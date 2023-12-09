<script setup>
import OtherError from "@/components/data-details/errors/OtherError.vue";
import ViolationErrors from "../../../data-details/errors/ViolationError.vue";
import ValidationError from "@/components/data-details/errors/ValidationError.vue";
import { js2xml } from 'xml-js';

</script>

<template>
  <div class="container">
    <div class="left-side">
      <p class="description_text">Добавление организации</p>
      <form @submit="submitForm" class="form">
        <div class="form-group">
          <div class="another-field">
            <label for="name">Имя</label>
            <input type="text" id="name" v-model="formData.name">
          </div>
          <div class="another-field">
            <label for="x">координата X</label>
            <input type="text" id="x" v-model="formData.coordinates.x">
          </div>
          <div class="another-field">
            <label for="y">координата Y</label>
            <input type="number" id="y" v-model="formData.coordinates.y">
          </div>
          <div class="another-field">
            <label for="annualTurnover">Годовой оборот</label>
            <input type="text" id="annualTurnover" v-model="formData.annualTurnover">
          </div>
          <div class="another-field">
            <label for="fullName">Полное Имя</label>
            <input type="text" id="fullName" v-model="formData.fullName">
          </div>
          <div class="another-field">
            <label for="employeesCount">Работники</label>
            <input type="text" id="employeesCount" v-model="formData.employeesCount">
          </div>
          <div class="another-field">
            <label for="type">Тип организации</label>
            <select id="type" v-model="formData.type">
              <option value="COMMERCIAL">COMMERCIAL</option>
              <option value="PUBLIC">PUBLIC</option>
              <option value="GOVERNMENT">GOVERNMENT</option>
              <option value="PRIVATE_LIMITED_COMPANY">PRIVATE_LIMITED_COMPANY</option>
            </select>
          </div>
          <div class="another-field">
            <label for="address">Адрес(улица)</label>
            <input type="text" id="address" v-model="formData.postalAddress.street">
          </div>
          <div class="another-field">
            <label for="zipcode">Индекс</label>
            <input type="text" id="zipcode" v-model="formData.postalAddress.zipCode">
          </div>
        </div>
        <button type="submit">Добавить организацию</button>
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
import OrganizationFromDto from "@/components/data-details/OrganizationFromDto.vue";
import ErrorDto from "@/components/data-details/errors/ErrorDto.vue";
import {headers, urls} from "@/configs/Config";
import {handleAxiosError} from "@/components/requests/ErrorHandler";
import '@/assets/requets.css';
import {
  addToValidationsAnotherError, validateAnnualTurnover,
  validateCoordinates,
  validateName
} from "@/components/utils/validate";
import {js2xml} from "xml-js";
import {XMLParser} from "fast-xml-parser";

export default {

  components: {
    OrganizationFromDto,
    ErrorDto
  },

  data() {
    return {
      formData: {
        name: '',
        coordinates: {
          x: 0,
          y: 0
        },
        annualTurnover: 0,
        fullName: '',
        employeesCount: 0,
        type: 'PUBLIC',
        postalAddress: {
          street: "Kosovo street",
          zipCode: "11822-0403"
        }
      },

      errorAll: null,
      organization: null
    };
  },

  methods: {
    validateAll() {
      if (!validateName(this.formData.name)) {
        const validError = {
          fieldName: 'name',
          message: 'name must be not null and not blank'
        };
        this.errorAll = addToValidationsAnotherError(this.errorAll, validError);
      }
      if (!validateName(this.formData.fullName)) {
        const validError = {
          fieldName: 'name',
          message: 'name must be not null and not blank'
        };
        this.errorAll = addToValidationsAnotherError(this.errorAll, validError);
      }

      if (!validateCoordinates(this.formData.coordinates)) {
        const validError = {
          fieldName: 'coordinates: x and y',
          message: 'x is double, y is integer, both is required.'
        };
        this.errorAll = addToValidationsAnotherError(this.errorAll, validError);
      }

      if (!validateAnnualTurnover(this.formData.annualTurnover)) {
        const validError = {
          fieldName: 'annualTurnover',
          message: 'annualTurnover must be not null and >0'
        };
        this.errorAll = addToValidationsAnotherError(this.errorAll, validError);
      }

      if (!validateName(this.formData.postalAddress.zipCode)) {
        const validError = {
          fieldName: 'zipCode',
          message: 'zipCode must be not null and not blank'
        };
        this.errorAll = addToValidationsAnotherError(this.errorAll, validError);
      }

      if (!validateName(this.formData.postalAddress.street)) {
        const validError = {
          fieldName: 'street',
          message: 'street must be not null and not blank'
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

      const parser = new XMLParser()
      const xml = "<organization>" + OBJtoXML(this.formData) + "</organization>"
      axios.create()
          .post(`${urls[0]}/organizations`, xml, {headers: {'Content-Type': 'application/xml'}})
          .then(response => {
            console.log(response);
            this.organization = parser.parse(response.data).organization
          })
          .catch(error => {
            console.log(error);
            this.errorAll = handleAxiosError(error);
          });
    },
  },
};

function OBJtoXML(obj) {
  var xml = '';
  for (var prop in obj) {
    xml += "<" + prop + ">";
    if (Array.isArray(obj[prop])) {
      for (var array of obj[prop]) {

        // A real botch fix here
        xml += "</" + prop + ">";
        xml += "<" + prop + ">";

        xml += OBJtoXML(new Object(array));
      }
    } else if (typeof obj[prop] == "object") {
      xml += OBJtoXML(new Object(obj[prop]));
    } else {
      xml += obj[prop];
    }
    xml += "</" + prop + ">";
  }
  var xml = xml.replace(/<\/?[0-9]{1,}>/g, '');
  return xml
}
</script>

<style scoped>
@import "@/assets/forms-inputs.css";
</style>