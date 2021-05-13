<template>
  <div>
    <div class="container">
      <div class="search">Votre city tour</div>
    </div>
    <div class="bottom-container">
      <div class="break"></div>
      <div class="field-form">
        <label for="name"> by year</label>
        <div class="break"></div>
        <select
          name="name"
          id="year-select"
          v-model="selectedYear"
          @click="onSelectYear()"
        >
          <option value="" selected disabled hidden>Choix de l'année</option>
          <option value="2016">2016</option>
          <option value="2017">2017</option>
          <option value="2018">2018</option>
          <option value="2019">2019</option>
          <option value="2020">2020</option>
        </select>
      </div>
      <div class="break"></div>
      <br />

      <!-- :pathBetweenPositions="pathBetweenPositions" -->
      <Map :infoPositions="infoPositions" v-if="infoPositions.length > 0"></Map>
      <br />
    </div>
  </div>
</template>

<script>
import Map from "../map/Map.vue";

export default {
  name: "FilmByYear",

  components: {
    Map,
  },

  props: {},

  data: () => {
    return {
      selectedYear: "",
      infoPositions: [],
      pathBetweenPositions: [],
    };
  },

  mounted() {},

  created() {},

  updated() {},

  methods: {
    onSelectYear() {
      if (this.selectedYear !== "") {
        this.executeSparqlQueryMedia(this.selectedYear);
        this.selectedYear = "";
      }
    },

     executeSparqlQueryMedia(yearChoosed) {
      this.infoPositions = [];
      const encodedQuery = encodeURIComponent(`PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> 
         PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> 
         PREFIX :<http://www.semanticweb.org/cassia/ontologies/2016/FilmsToulouse> 
         SELECT * WHERE { 
             ?media :a_ete_tourne_en ?annee.
             ?media :a_ete_tournee_dans_le_lieux ?lieux. 
             ?media :a_ete_tournee_a_la_position ?position.  
             ?media :a_pour_nom ?nom.        
             FILTER(?annee = "${yearChoosed}")   
         } LIMIT 50`);

      this.axios
        .post(`http://localhost:3030/film/query?query=${encodedQuery}`)
        .then((response) => {
          response.data.results.bindings.map((elem) => {
            this.infoPositions.push({
              filmName: elem.nom.value,
              lieux: elem.lieux.value,
              long: elem.position.value.split(";")[0],
              lat: elem.position.value.split(";")[1],
            });

            //   this.pathBetweenPositions.push([
            //     elem.position.value.split(";")[0],
            //     elem.position.value.split(";")[1],
            //   ]);
          });
        });
    },
  },
};
</script>

<style scoped>
.break {
  flex-basis: 100%;
  height: 0;
}

.field-form {
  width: 100%;
  margin-top: 1%;
  margin-left: 1%;
  font-size: 1em;
  font-family: "Roboto", sans-serif;
  color: #cdd9e5;
}

.field {
  font-size: 1em;
  font-family: "Roboto", sans-serif;
  color: #cdd9e5;
  width: 50%;
  margin-top: 1%;
  margin-left: 1%;
  margin-right: 1%;
}
</style>
