<template>
  <div>
    <div class="container">
      <div class="search">Votre city tour</div>
    </div>
    <div class="bottom-container">
      <div class="break"></div>
      <div class="field-form">
        <label for="name"> by media</label>
        <div class="break"></div>

        <select
          name="name"
          id="media-select"
          v-model="selectedMedia"
          @click="onSelectMedia()"
        >
          <option value="" selected disabled hidden>Choix du media</option>
          <option value="film">Film</option>
          <option value="serieWeb">Serie WEB</option>
          <option value="serieTV">Serie TV</option>
        </select>

        <!-- :pathBetweenPositions="pathBetweenPositions" -->
        <Map :infoPositions="infoPositions" v-if="infoPositions.length > 0"></Map>
        <br />
      </div>
    </div>
  </div>
</template>

<script>
import Map from "../map/Map.vue";

export default {
  name: "FilmByMedia",

  components: {
    Map,
  },

  props: {},

  data: () => {
    return {
      selectedMedia: "",
      infoPositions: [],
      pathBetweenPositions: [],
    };
  },

  mounted() {},

  created() {},

  updated() {},

  methods: {
    onSelectMedia() {
      if (this.selectedMedia !== "") {
        console.log(this.selectedMedia);
        if (this.selectedMedia === "film") {
          this.executeSparqlQueryMedia(this.selectedMedia);
          this.selectedMedia = "";
        } else if (this.selectedMedia === "serieWeb") {
          this.executeSparqlQueryMedia(this.selectedMedia);
          this.selectedMedia = "";
        } else if (this.selectedMedia === "serieTV") {
          this.executeSparqlQueryMedia(this.selectedMedia);
          this.selectedMedia = "";
        }
      }
    },

    executeSparqlQueryMedia(mediaChoosed) {
      this.infoPositions = [];
      const encodedQuery = encodeURIComponent(`PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> 
         PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> 
         PREFIX :<http://www.semanticweb.org/cassia/ontologies/2016/FilmsToulouse> 
         SELECT * WHERE { 
            ?media rdfs:${mediaChoosed} ?Media. 
            ?media :a_ete_tournee_dans_le_lieux ?lieux. 
            ?media :a_ete_tournee_a_la_position ?position. 
            ?media :a_pour_nom ?nom.    
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
