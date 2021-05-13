import { createWebHistory, createRouter } from "vue-router";
import FilmByMark from "../components/feature/FilmByMark/FilmByMark.vue";
import FilmByMedia from "../components/feature/FilmByMedia/FilmByMedia.vue";
import FilmByYear from "../components/feature/FilmByYear/FilmByYear.vue";
import FilmByTitle from "../components/feature/FilmByTitle/FilmByTitle.vue";

const routes = [
  {
    path: "/",
    name: "mark",
    component: FilmByMark,
  },
  {
    path: "/media",
    name: "media",
    component: FilmByMedia,
  },
  {
    path: "/year",
    name: "year",
    component: FilmByYear,
  },
  {
    path: "/title",
    name: "title",
    component: FilmByTitle,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;