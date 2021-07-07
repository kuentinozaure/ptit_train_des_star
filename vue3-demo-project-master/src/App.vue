<template>
  <div class="body">
    <nav-bar></nav-bar>
    <br/>
   <router-view />
  </div>
</template>

<script>
import NavBar from "./components/core/NavBar.vue";

export default {
  components: {
    NavBar,
  },
  setup() {
    return {};
  },
};
</script>

<style >

@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@100&display=swap');

#app {
  background-color: #22272e;
  color: #cdd9e5;
}
</style>
