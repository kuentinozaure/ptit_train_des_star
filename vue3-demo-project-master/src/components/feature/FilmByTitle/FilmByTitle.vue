<template>
  <div>
    <div class="container">
      <div class="search">Votre city tour</div>
    </div>
    <div class="bottom-container">
      <div class="break"></div>
      <div class="field-form">
        <label for="name">By title</label>

        <input
          class="field"
          name="name"
          type="text"
          minlength="4"
          maxlength="100"
          size="100"
          v-model="typedFilm"
        />

        <button v-on:click="onClickSearch()">Search</button>
        <div class="break"></div>
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
  name: "FilmByTitle",

  components: {
    Map,
  },

  props: {},

  data: () => {
    return {
      typedFilm: "",
      infoPositions: [],
      pathBetweenPositions: [],
    };
  },

  mounted() {},

  created() {},

  updated() {},

  methods: {
    onClickSearch() {
      console.log(this.typedFilm)
      this.executeSparqlQueryMedia(this.typedFilm);
      this.typedFilm = "";
    },

    executeSparqlQueryMedia(typedFilm) {
      this.infoPositions = [];
      const encodedQuery = encodeURIComponent(`PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> 
         PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> 
         PREFIX :<http://www.semanticweb.org/cassia/ontologies/2016/FilmsToulouse> 
         SELECT * WHERE { 
            ?media :a_ete_tournee_dans_le_lieux ?lieux. 
            ?media :a_ete_tournee_a_la_position ?position.  
            ?media :a_pour_nom ?nom.    
            FILTER regex(?nom, "${typedFilm}", "i"). 
         } LIMIT 50`);

      this.axios
        .post(`http://localhost:3030/film/query?query=${encodedQuery}`)
        .then((response) => {
          response.data.results.bindings.map((elem) => {
            console.log(elem);
            this.infoPositions.push({
              filmName: elem.nom.value,
              lieux: elem.lieux.value,
              long: elem.position.value.split(";")[0],
              lat: elem.position.value.split(";")[1],
            });

             this.pathBetweenPositions.push([
            elem.position.value.split(";")[0],
            elem.position.value.split(";")[1],
          ]);
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
