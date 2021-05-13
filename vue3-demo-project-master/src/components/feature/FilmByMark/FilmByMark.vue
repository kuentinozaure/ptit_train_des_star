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
      <select name="name" id="media-select">
        <option value="film">Film</option>
        <option value="webSeries">Web series</option>
        <option value="TvMovie">TV movie</option>
      </select>

      <div class="break"></div>

      <label for="name"> by media</label>
      <div class="break"></div>
      <select name="name" id="year-select">
        <option value="2016">2016</option>
        <option value="2017">2017</option>
        <option value="2018">2018</option>
        <option value="2019">2019</option>
        <option value="2020">2020</option>
      </select>

      <div class="break"></div>

      <label for="name">By title</label>

      <input
        class="field"
        name="name"
        type="text"
        minlength="4"
        maxlength="8"
        size="10"
      />

      <button v-on:click="onClickSearch('Taxi 5')">Search</button>
      <div class="break"></div>
    </div>
    <div class="break"></div>
    <br />

    <!-- :pathBetweenPositions="pathBetweenPositions" -->
    <Map :infoPositions="infoPositions"></Map>
    <br />
  </div>
    </div>
</template>

<script>
import Map from "../map/Map.vue";

export default {
  name: "FilmByMark",

  components: {
    Map,
  },

  props: {},

  data: () => {
    return {
      infoPositions: [],
      pathBetweenPositions: [],
    };
  },

  mounted() {
    const encodedQuery = encodeURIComponent(
      "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> PREFIX : <http://www.semanticweb.org/cassia/ontologies/2016/FilmsToulouse> PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> SELECT  * WHERE { ?film rdfs:film ?y. ?film :a_pour_nom ?nom. ?film :a_pour_note ?note. ?film :a_ete_tournee_a_la_position ?position. ?film :a_ete_tournee_dans_le_lieux ?lieux. FILTER ( ?note > 5 ). } LIMIT 100"
    );
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

          this.pathBetweenPositions.push([
            elem.position.value.split(";")[0],
            elem.position.value.split(";")[1],
          ]);
        });
      });

    console.log(this.infoPositions, this.pathBetweenPositions);
  },

  created() {},

  updated() {},

  methods: {
    onClickSearch(value) {
      const encodedQuery = encodeURIComponent(
        `PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> PREFIX : <http://www.semanticweb.org/cassia/ontologies/2016/FilmsToulouse> PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> SELECT  * WHERE { ?film :a_pour_nom "${value}"^^xsd:string. ?film :a_ete_tournee_a_la_position ?position. ?film :a_ete_tournee_dans_le_lieux ?lieux. ?film :a_pour_nom ?nomFilm. } `
      );
      this.axios
        .post(`http://localhost:3030/film/query?query=${encodedQuery}`)
        .then((response) => {
            response.data.results.bindings.map(elem => {
                console.log(elem)
                 this.infoPositions.push({
            filmName: elem.nomFilm.value,
            lieux: elem.lieux.value,
            long: elem.position.value.split(";")[0],
            lat: elem.position.value.split(";")[1],
          });
            })
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
