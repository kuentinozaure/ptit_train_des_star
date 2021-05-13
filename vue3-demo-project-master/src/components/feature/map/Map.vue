<template>
  <div id="root-map" class="root-map">
    <l-map
      class="map"
      :zoom="zoom"
      :center="[centerLatMap, centerLonMap]"
      @move="log('move')"
    >
      <!-- url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png" -->
      <l-tile-layer
        url="https://api.mapbox.com/styles/v1/tigri9/cka2hmpmm3jze1iobd8vefv6z/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoidGlncmk5IiwiYSI6ImNrYTJndGY2NDAzMjMza2xrZHZvbWtqOTgifQ.M0zsMOzbE-vrjD08Jij7eg"
      ></l-tile-layer>
      <l-control-layers />
      <!-- lat-lng est l'argument pour poser le marqueur. Il faut fournir un array [lat,long] -->

        <!-- <l-polyline
          :lat-lngs="pathBetweenPositions"
          :color="green"
        ></l-polyline> -->

        <l-marker :lat-lng="[item.long, item.lat]"  v-for="(item, index) in infoPositions" :key="index">
          <l-icon :icon-url="iconUrl" :icon-size="iconSize" />
          <l-popup offset="(15,0)">
            <h2>{{ item.filmName }}</h2>
            <p>{{ item.lieux }}</p>
          </l-popup>
        </l-marker>
    </l-map>
  </div>
</template>

<script>
import {
  LMap,
  LIcon,
  LTileLayer,
  LMarker,
  LControlLayers,
  LPopup,
  // LPolyline,
} from "@vue-leaflet/vue-leaflet";
import "leaflet/dist/leaflet.css";

import { reactive, toRefs, computed } from "vue";
import Marquer from "../../../assets/marquer.png";
export default {
  components: {
    LMap,
    LIcon,
    LTileLayer,
    LMarker,
    LControlLayers,
    LPopup,
    // LPolyline,
  },

  props: {
    infoPositions: [],
    pathBetweenPositions: [],
  },

    data: () => {
    return {
    };
  },

  mounted() {
  },

  setup() {
    const state = reactive({
      zoom: 12,
      centerLatMap: 48.8529,
      centerLonMap: 2.3504,
      iconWidth: 25,
      iconHeight: 40,
    });

    const log = (msg) => {
      console.log(msg);
    };
    const iconSize = computed(() => {
      return [state.iconWidth, state.iconHeight];
    });
    const iconUrl = computed(() => {
      return Marquer;
    });
    return {
      ...toRefs(state),
      log,
      iconSize,
      iconUrl,
    };
  },


  methods: {
  },
};
</script>

<style scoped>
#root-map {
  height: 75vh;
  width: 80%;
  margin-left: 10%;
  margin-right: 10%;
  border-radius: 10px;
  border: 1px solid #444c56;
}

.map {
  border-radius: 10px;
}
</style>