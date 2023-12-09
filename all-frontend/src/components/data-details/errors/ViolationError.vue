<script setup>

</script>

<template>
  <div class="error">
    <h2>Error Details VIOLATIONS</h2>
    <div class="error-info">
      <div v-for="(violation, index) in errors" :key="index">
        <p><strong>Error with field equals:</strong> {{ violation.fieldName }}</p>
        <p><strong>Message about:</strong> {{ violation.message }}</p>
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