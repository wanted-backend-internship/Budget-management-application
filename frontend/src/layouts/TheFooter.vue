<script setup lang="ts">
import { library } from '@fortawesome/fontawesome-svg-core';
import { faUser } from '@fortawesome/free-regular-svg-icons';
import {
  faChartPie,
  faReceipt,
  faChartSimple,
} from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
library.add(faUser, faChartPie, faChartSimple, faReceipt);
</script>

<template>
  <div class="container-col">
    <font-awesome-icon :icon="['far', 'user']" class="icon" />
    <div class="font_menu">대시 보드</div>
  </div>
  <div class="container-col">
    <font-awesome-icon icon="fa-solid fa-chart-pie" class="icon" />
    <div class="font_menu">예산 설정</div>
  </div>
  <div class="container-col">
    <font-awesome-icon icon="fa-solid fa-receipt" class="icon" />
    <div class="font_menu">지출 등록</div>
  </div>
  <div class="container-col">
    <font-awesome-icon icon="fa-solid fa-chart-simple" class="icon" />
    <div class="font_menu">지출 통계</div>
  </div>
</template>

<style scoped lang="scss">
@import '../assets/styles/common/container';
@import '../assets/styles/common/typho.scss';
@import '../assets/styles/color/_color.scss';

.container-col {
  @include container(column, center, center, auto, auto);
}

.font_menu {
  @include pre300(18px, $black);
  margin: 10px 10px 35px 10px;
}

.icon {
  margin-top: 20px;
  font-size: 30px;
}
</style>
