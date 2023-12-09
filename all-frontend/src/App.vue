<script setup>

import Header from "@/components/headers-and-static/Header.vue";
import NavigationAndServices from "@/components/headers-and-static/NavigationAndServices.vue";
</script>

<template>
  <div class="all-page">
    <div class="our-headline">
      <Header/>
    </div>

    <div class="telo">
      <NavigationAndServices/>
    </div>
  </div>
</template>

<style scoped>
.all-page {
  min-width: 710px;
  width: 100%;
  height: 100%;
}

.our-headline {
  display: inline-flex;
  position: fixed;
  top: 0;
  left: 0;
}

.telo {
  margin-top: 60px;
  overflow: auto;
}

</style>
