<script setup>

</script>

<template>
  <div class="error">
    <h2>Error Details</h2>
    <div class="error-info">
      <p><strong>Status:</strong> {{ error.status }}</p>
      <p><strong>Error:</strong> {{ error.error }}</p>
      <p><strong>Timestamp:</strong> {{ getCorrectTime() }}</p>
    </div>
  </div>
</template>

<script>
import { convertTimestampToDate } from "@/components/utils/utils";

export default {
  props: {
    error: Object, // Принимайте объект ошибки в качестве свойства
  },

  data() {
    return {
      timeWithDate: ""
    }
  },

  methods: {
    getCorrectTime() {
      if (this.error && this.error.time) {
        this.timeWithDate = convertTimestampToDate(this.error.time);
      } else {
        this.timeWithDate = "no load date";
      }
      return this.timeWithDate;
    }

  }
};

</script>

<style scoped>
@import "@/assets/error-styles.css";
</style>