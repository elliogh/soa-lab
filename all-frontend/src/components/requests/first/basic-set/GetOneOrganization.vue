<script setup>

import OtherError from "@/components/data-details/errors/OtherError.vue";
import ViolationError from "../../../data-details/errors/ViolationError.vue";
import ValidationError from "@/components/data-details/errors/ValidationError.vue";
</script>

<template>
  <div class="container">
    <div class="left-side">
      <p class="description_text">Получение организации по id</p>
      <form @submit="submitForm" class="form">
        <div class="form-group">
          <div class="another-field">
            <label for="id">id</label>
            <input type="number" id="id" v-model="formData.id">
          </div>
        </div>
        <button type="submit">Найти организацию</button>
      </form>
    </div>
    <div class="right-side">
      <div v-if="errorAll" class="error-message">
        <div v-if="errorAll.violations">
          <ViolationError :errors="errorAll.violations"/>
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
import OrganizationFromDto from "@/components/data-details/OrganizationFromDto.vue";
import ErrorDto from "@/components/data-details/errors/ErrorDto.vue";
import {headers, urls} from "@/configs/Config";
import {handleAxiosError} from "@/components/requests/ErrorHandler";
import '@/assets/requets.css';
import {addToValidationsAnotherError, validateId} from "@/components/utils/validate";
import {XMLParser} from 'fast-xml-parser'

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
    validateAll() {
      if (!validateId(this.formData.id)) {
        const validError = {
          fieldName: 'id',
          message: 'id must be not null and >0'
        };
        this.errorAll = addToValidationsAnotherError(this.errorAll, validError);
      }
    },

    submitForm(event) {
      event.preventDefault();

      // Сбросил вывод о прошлом действии
      this.organization = null
      this.errorAll = null

      this.validateAll();
      if (this.errorAll && this.errorAll.validations) {
        return;
      }
      const parser = new XMLParser();
      axios.create()
          .get(`${urls[0]}/organizations/${this.formData.id}`)
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