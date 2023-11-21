<script setup lang="ts">
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { library } from '@fortawesome/fontawesome-svg-core';
import {
  faEnvelopesBulk,
  faMagnifyingGlassDollar,
} from '@fortawesome/free-solid-svg-icons';
library.add(faMagnifyingGlassDollar, faEnvelopesBulk);
</script>

<template>
  <div class="container-col">
    <div class="menu-box">
      <router-link to="/expenditures/add" class="content-row">
        <font-awesome-icon
          icon="fa-solid fa-envelopes-bulk"
          style="margin-right: 10px"
        />
        <div>지출 등록</div>
      </router-link>
    </div>
    <div class="menu-box">
      <router-link to="/expenditures/search" class="content-row">
        <font-awesome-icon
          icon="fa-solid fa-magnifying-glass-dollar"
          style="margin-right: 10px"
        />
        <div>지출 조회</div>
      </router-link>
    </div>
  </div>
</template>

<style scoped lang="scss">
@import '../assets/styles/common/container';
@import '../assets/styles/common/button';
@import '../assets/styles/color/_color';
@import '../assets/styles/common/typho';

.container-col {
  @include container(column, flex-start, center, 100%, 100%);
  padding-top: 40px;
}

.menu-box {
  @include container(column, flex-start, flex-start, 80%, 90px);
  @include pre200(24px, $black);
  background-color: white;
  margin-bottom: 20px;
  border-radius: 20px;
}

.content-row {
  @include container(row, flex-start, center, 100%, 100%);
  margin-left: 50px;
  text-decoration: none;
  color: inherit;
}
</style>
