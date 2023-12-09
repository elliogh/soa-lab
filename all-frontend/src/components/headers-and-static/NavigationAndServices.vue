<template>
  <div class="navigation">
    <div class="trapezoid-first">
      <button type="button" class="my-button" @click="component='First'">FIRST SERVICE</button>
    </div>
    <div class="trapezoid-second">
      <button type="button" class="my-button" @click="component='Second'">SECOND SERVICE</button>
    </div>
  </div>


  <div class="service-wrapper">
    <div class="service-content">
      <KeepAlive>
        <component :is="component"></component>
      </KeepAlive>
    </div>
  </div>
</template>

<script>
import First from "@/components/First.vue";
import Second from "@/components/Second.vue";

export default {
  components: {
    First,
    Second
  },

  data() {
    return {
      component: 'First'
    }
  }
}

</script>

<style scoped>
@import "@/assets/navigation-and-buttons.css";

.service-content {

}

.service-wrapper {
  width: 100%;
  overflow: auto;
}

</style>