<template>
  <div>
    <div class="container">
      <div class="title"><b>LePtitTrainDesStars</b></div>
    </div>
    <div class="bottom-container">
      <router-link to="/" class="tab">Recherche par notes</router-link>
      <router-link to="/media" class="tab">Recherche par media</router-link>
      <router-link to="/year" class="tab">Recherche par années</router-link>
      <router-link to="/title" class="tab">Recherche par titre de film</router-link>
    </div>
    <br/>
  </div>
</template>

<script>
export default {
  name: "NavBar",

  props: {},

  components: {},

  data: () => {
    return {
      choosedTab: "",
    };
  },

  mounted() {},

  created() {},

  updated() {},

  methods: {},
};
</script>

<style scoped>
.container {
  display: flex;
  background-color: #2d333b;
  border-top-left-radius: 6px;
  border-top-right-radius: 6px;
  border: 1px solid #444c56;
}

.bottom-container {
  display: flex;
  flex-wrap: wrap;
  background-color: #2d333b;
  border-bottom-left-radius: 6px;
  border-bottom-right-radius: 6px;
  border: 1px solid #444c56;
}

.title {
  margin-top: 1%;
  margin-bottom: 1%;
  margin-left: 1%;
  font-size: 2em;
  font-family: "Roboto", sans-serif;
  color: #cdd9e5;
}

.tab {
  margin-top: 0.5%;
  margin-bottom: 0.5%;
  margin-left: 1%;
  font-size: 1rem;
  font-family: "Roboto", sans-serif;
  color: #cdd9e5;
  width: 23%;
  text-align: center;
}

.tab:hover {
  cursor: pointer;
}

</style>
