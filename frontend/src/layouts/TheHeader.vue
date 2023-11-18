<script setup lang="ts">
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { library } from '@fortawesome/fontawesome-svg-core';
import {
  faBell,
  faArrowRightToBracket,
  faArrowRightFromBracket,
} from '@fortawesome/free-solid-svg-icons';
library.add(faBell, faArrowRightToBracket, faArrowRightFromBracket);
</script>

<template>
  <div class="container-row">
    <div style="margin: 50px 0 30px 20px">
      <img src="../assets/images/logo-white.svg" width="120" />
    </div>
    <div style="margin: 50px 20px 30px 0">
      <div class="container-row">
        <font-awesome-icon
          icon="fa-solid fa-arrow-right-to-bracket"
          class="icon"
        />
        <font-awesome-icon
          icon="fa-solid fa-arrow-right-from-bracket"
          class="icon"
        />
        <font-awesome-icon :icon="['fas', 'bell']" class="icon" />
        <img src="../assets/images/user.svg" width="40" />
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
@import '../assets/styles/common/container';
@import '../assets/styles/color/_color.scss';
@import '../assets/styles/common/typho.scss';

.container-row {
  @include container(row, space-between, center, 100%, auto);
  background-color: $blue;
}

.icon {
  font-size: 20px;
  color: whitesmoke;
  margin-right: 15px;
}
</style>
