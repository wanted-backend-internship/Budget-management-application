<script setup lang="ts">
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { library } from '@fortawesome/fontawesome-svg-core';
import {
  faSackDollar,
  faChartPie,
  faSliders,
} from '@fortawesome/free-solid-svg-icons';
library.add(faSackDollar, faChartPie, faSliders);
</script>

<template>
  <div class="container-col">
    <div class="menu-box">
      <router-link to="/budgets/total" class="content-row">
        <font-awesome-icon
          icon="fa-solid fa-sack-dollar"
          style="margin-right: 10px"
        />
        <div>총 예산 설정</div>
      </router-link>
    </div>
    <div class="menu-box">
      <router-link to="/budgets/setting" class="content-row">
        <font-awesome-icon
          icon="fa-solid fa-sliders"
          style="margin-right: 10px"
        />
        <div>예산 세부 설정</div>
      </router-link>
    </div>
    <div class="menu-box">
      <router-link to="/budgets/me" class="content-row">
        <font-awesome-icon
          icon="fa-solid fa-chart-pie"
          style="margin-right: 10px"
        />
        <div>이번달 예산 확인</div>
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
  @include container(row, center, center, 100%, 100%);
  text-decoration: none;
  color: inherit;
}
</style>
