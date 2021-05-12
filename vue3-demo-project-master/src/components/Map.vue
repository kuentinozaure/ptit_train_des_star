<template>
    <div id="root-map">
    <l-map
      :zoom="zoom"
      :center="[centerLatMap, centerLonMap]"
      @move="log('move')"
    >
      <l-tile-layer
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      ></l-tile-layer>
      <l-control-layers />
      <!-- lat-lng est l'argument pour poser le marqueur. Il faut fournir un array [lat,long] -->
      <l-marker :lat-lng="[47.41322, -1.219482]">
        <l-icon :icon-url="iconUrl" :icon-size="iconSize" />
        <l-popup offset="(15,0)">
          <h1>TCHOU TCHOU</h1>
          <p>C'est le petit train</p>
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
  LPopup
} from "@vue-leaflet/vue-leaflet";
import "leaflet/dist/leaflet.css";
import { reactive, toRefs, computed } from 'vue';
import Marquer from "../assets/marquer.png";

export default {
    components: {
    LMap,
    LIcon,
    LTileLayer,
    LMarker,
    LControlLayers,
    LPopup
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

    const iconSize = computed(() =>{
        return [state.iconWidth, state.iconHeight];
    })

    const iconUrl = computed(() =>{
        return Marquer;
    })

    return {
        ...toRefs(state),
        log,
        iconSize,
        iconUrl
    }
  }
}
</script>

<style scoped>
#root-map{
    height: 75vh;
    width: 50vw;
}
</style>