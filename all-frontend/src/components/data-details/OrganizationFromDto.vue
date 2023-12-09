<script setup>

</script>

<template>
  <div v-if="organization">
    <div id="organization_view" class="organization_view">
      <h2 :data-tooltip="organization.name">{{ organization.name }}</h2>
      <p>ID: {{ organization.id }}</p>
      <p>Координаты: ({{ organization.coordinates.x }}, {{ organization.coordinates.y }})</p>
      <p>Дата создания: {{ new Date(organization.creationDate).toLocaleTimeString() }} {{ new Date(organization.creationDate).toLocaleDateString() }}</p>
      <p>Годовой оборот: {{ organization.annualTurnover }}</p>
      <p>Полное имя: {{ organization.fullName }}</p>
      <p>Работники: {{ organization.employeesCount }}</p>
      <p>Тип: {{ organization.type }}</p>
      <p>Адрес: {{ organization.postalAddress.street }}, {{ organization.postalAddress.zipCode }}</p>
    </div>
  </div>
  <div v-else>
    <p class="error_text">Данные организации не загружены.</p>
  </div>
</template>

<script>
export default {
  props: {
    organization: Object // Принимаем объект организации как проп
  }
};

</script>

<style scoped>
@import "@/assets/dto-style.css";
</style>