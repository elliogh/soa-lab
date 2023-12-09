<script setup>

import OtherError from "@/components/data-details/errors/OtherError.vue";
import ViolationErrors from "../../data-details/errors/ViolationError.vue";
import ValidationError from "@/components/data-details/errors/ValidationError.vue";
import OkResponseNoContent from "@/components/data-details/OkResponseNoContent.vue";
</script>

<template>
  <div class="container">
    <div class="left-side">
      <p class="description_text">Уволить всех</p>
      <form @submit="submitForm" class="form">
        <div class="form-group">
          <div class="another-field">
            <label for="id">ID</label>
            <input type="text" id="id" v-model="formData.id">
          </div>
        </div>
        <button type="submit">Тык</button>
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
import {urls} from "@/configs/Config";
import {handleAxiosError} from "@/components/requests/ErrorHandler";
import '@/assets/requets.css';

export default {

  components: {
    ErrorDto
  },

  data() {
    return {
      formData: {
        id: 1,
      },

      errorAll: null,
      message_result: ''
    };
  },

  methods: {

    submitForm(event) {
      event.preventDefault();

      // Сбросил вывод о прошлом действии
      this.message_result = null
      this.errorAll = null

      axios.create()
          .delete(`${urls[1]}/orgmanager/fire/all/${this.formData.id}`)
          .then(response => {
            this.message_result = "Все уволены";
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