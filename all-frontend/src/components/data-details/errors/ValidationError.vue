<script setup>

</script>

<template>
  <div class="error">
    <h2>Error Details VALIDATION</h2>
    <div class="error-info">
      <div v-for="(validation, index) in errors" :key="index">
        <p><strong>Error with field:</strong> {{ validation.fieldName }}</p>
        <p><strong>What's wrong:</strong> {{ validation.message }}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    errors: Object, // Принимайте объект ошибки в качестве свойства
  },

};
</script>

<style scoped>
@import "@/assets/error-styles.css";
</style>