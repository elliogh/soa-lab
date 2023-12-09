<script setup>

import OtherError from "@/components/data-details/errors/OtherError.vue";
import OkResponseNoContent from "@/components/data-details/OkResponseNoContent.vue";
import ViolationErrors from "../../../data-details/errors/ViolationError.vue";
import ValidationError from "@/components/data-details/errors/ValidationError.vue";
</script>

<template>
  <div class="container">
    <div class="left-side">
      <p class="description_text">Удаление организации по id</p>
      <form @submit="submitForm" class="form">
        <div class="form-group">
          <div class="another-field">
            <label for="id">id</label>
            <input type="number" id="id" v-model.number="formData.id">
          </div>
        </div>
        <button type="submit">Удалить организацию</button>
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
        <OkResponseNoContent :message_for_no_content="message_result"/>
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
import {addToValidationsAnotherError, validateId} from "@/components/utils/validate";

export default {

  components: {
    ErrorDto
  },

  data() {
    return {
      formData: {
        id: '',
      },

      errorAll: null,
      message_result: ''
    }
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
      this.message_result = null
      this.errorAll = null

      this.validateAll();
      if (this.errorAll && this.errorAll.validations) {
        return;
      }

      console.log(`${urls[0]}/organizations/${this.formData.id}`, {headers})
      axios.create()
          .delete(`${urls[0]}/organizations/${this.formData.id}`)
          .then(response => {
            this.message_result = "Организация удалена.";
          })
          .catch(error => {
            console.log(error)
            this.errorAll = handleAxiosError(error);
          });
    },
  },
};
</script>

<style scoped>
@import "@/assets/forms-inputs.css";
</style>