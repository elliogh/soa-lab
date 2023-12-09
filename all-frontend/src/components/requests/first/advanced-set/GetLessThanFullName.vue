<script setup>

import OtherError from "@/components/data-details/errors/OtherError.vue";
import ViolationErrors from "../../../data-details/errors/ViolationError.vue";
import ValidationError from "@/components/data-details/errors/ValidationError.vue";
</script>

<template>
  <div class="container">
    <div class="left-side">
      <p class="description_text">Организации, значение поля fullName которых меньше заданного</p>
      <form @submit="submitForm" class="form">
        <div class="form-group">
          <div class="another-field">
            <label for="fullName">fullName</label>
            <input type="text" id="fullName" v-model="formData.fullName">
          </div>
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
        <div v-for="(organization, index) in organizations" :key="index">
          <OrganizationFromDto :organization="organization" />
        </div>
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
import {
  addToValidationsAnotherError, validateAnnualTurnover,
  validateCoordinates,
  validateCreationDate,
  validateName
} from "@/components/utils/validate";
import OrganizationFromDto from "@/components/data-details/OrganizationFromDto.vue";
import {XMLParser} from "fast-xml-parser";

export default {

  components: {
    OrganizationFromDto,
    ErrorDto
  },

  data() {
    return {
      formData: {
        fullName: ''
      },

      errorAll: null,
      organizations: null
    }
  },

  methods: {
    submitForm(event) {
      event.preventDefault();

      // Сбросил вывод о прошлом действии
      this.organizations = null
      this.errorAll = null

      const url = `${urls[0]}/organizations/less-than-fullname?fullname=${this.formData.fullName}`
      console.log(url)

      const parser = new XMLParser();
      axios.create()
          .get(url)
          .then(response => {
            this.organizations = parser.parse(response.data).organizations.organization
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