<script setup>

import OtherError from "@/components/data-details/errors/OtherError.vue";
import ViolationErrors from "../../../data-details/errors/ViolationError.vue";
import ValidationError from "@/components/data-details/errors/ValidationError.vue";
</script>

<template>
  <div class="container">
    <div class="left-side">
      <p class="description_text">Объект, с минимальным postalAddress</p>
      <form @submit="submitForm" class="form">
        <div class="form-group">

        </div>
        <button type="submit">Получить</button>
      </form>
    </div>
    <div class="right-side">
      <div v-if="errorAll" class="error-message">
        <div v-if="errorAll.violations">
          <ViolationErrors :errors="errorAll.violations"/>
        </div>

        <div v-else-if="errorAll.validations">
          <ValidationError :errors="errorAll.validations"/>
        </div>

        <div v-else-if="errorAll.status" class="other-message">
          <OtherError :error="errorAll"/>
        </div>

        <div v-else>
          <ErrorDto :error="errorAll"/>
        </div>
      </div>
      <div v-else>
        <OrganizationFromDto :organization="organization"/>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import ErrorDto from "@/components/data-details/errors/ErrorDto.vue";
import {headers, urls} from "@/configs/Config";
import {handleAxiosError} from "@/components/requests/ErrorHandler";
import '@/assets/requets.css';
import {XMLParser} from "fast-xml-parser";
import OrganizationFromDto from "@/components/data-details/OrganizationFromDto.vue";

export default {

  components: {
    OrganizationFromDto,
    ErrorDto
  },

  data() {
    return {
      formData: {
        id: '',
      },

      errorAll: null,
      organization: null
    };
  },

  methods: {
    submitForm(event) {
      event.preventDefault();

      // Сбросил вывод о прошлом действии
      this.organization = null
      this.errorAll = null

      const parser = new XMLParser();
      axios.create()
          .get(`${urls[0]}/organizations/minimum-postal-address`)
          .then(response => {
            this.organization = parser.parse(response.data).organization
          })
          .catch(error => {
            this.errorAll = handleAxiosError(error);
          });
    },
  },
};
</script>

<style scoped>
@import "@/assets/forms-inputs.css";

</style>