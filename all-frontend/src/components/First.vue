<script setup>
import GetOneOrganization from "@/components/requests/first/basic-set/GetOneOrganization.vue";
import DeleteOneOrganization from "@/components/requests/first/basic-set/DeleteOneOrganization.vue";
import PostOneOrganization from "@/components/requests/first/basic-set/PostOneOrganization.vue";
import PutOneOrganization from "@/components/requests/first/basic-set/PutOneOrganization.vue";
import GetOrganizationsWithPreferences from "@/components/requests/first/basic-set/GetOrganizationsWithPreferences.vue";
import GetMaximumEmployeesCount from "@/components/requests/first/advanced-set/GetMaximumEmployeesCount.vue";
import GetLessThanFullName from "@/components/requests/first/advanced-set/GetLessThanFullName.vue";
import GetAllOrganizations from "@/components/requests/first/basic-set/GetAllOrganizations.vue";
import GetMinimumPostalAddress from "@/components/requests/first/advanced-set/GetMinimumPostalAddress.vue";
</script>

<template>
  <div class="overflow-wrapper">
    <GetOneOrganization/>
    <DeleteOneOrganization/>
    <PostOneOrganization/>
    <PutOneOrganization/>
    <GetAllOrganizations/>
    <GetOrganizationsWithPreferences/>
    <GetMinimumPostalAddress/>
    <GetMaximumEmployeesCount/>
    <GetLessThanFullName/>
  </div>
</template>

<script>
</script>

<style scoped>

</style>