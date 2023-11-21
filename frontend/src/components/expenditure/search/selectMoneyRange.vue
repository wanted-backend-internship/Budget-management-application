<script setup lang="ts">
import { ref, defineEmits } from 'vue';

const minMoney = ref(0);
const maxMoney = ref(0);
const emits = defineEmits(['update-range']);

function submitRange() {
  emits('update-range', { min: minMoney.value, max: maxMoney.value });
}
</script>

<template>
  <div class="container-col">
    <form class="content-col">
      <div class="form-col">
        <div class="font_title">금액으로 조회하기</div>
        <div class="font-label">최소금액과 최대 금액을 설정해주세요.</div>
        <div class="content-row">
          <input
            class="input"
            type="number"
            v-model="minMoney"
            style="width: 100px; margin-right: 10px"
          />
          <span>~</span>
          <input
            class="input"
            type="number"
            v-model="maxMoney"
            style="width: 100px; margin-left: 10px"
          />
        </div>
        <div type="submit" class="button-blue" @click="submitRange">
          선택완료
        </div>
      </div>
    </form>
  </div>
</template>

<style scoped lang="scss">
@import '../../../assets/styles/common/container';
@import '../../../assets/styles/common/button';
@import '../../../assets/styles/common/input';
@import '../../../assets/styles/color/_color';
@import '../../../assets/styles/common/typho';

.container-col {
  @include container(column, flex-start, flex-start, 100%, 100%);
  background-color: $background;
}

.content-col {
  @include container(column, flex-start, center, 100%, auto);
}

.content-row {
  @include container(row, flex-start, center, 100%, auto);
  margin-bottom: 10px;
}

.select-box {
  @include container(row, flex-start, center, 100%, auto);
  padding: 2px;
}

.form-col {
  @include container(column, flex-start, flex-start, 310px, auto);
  padding-left: 5px;
}

.button-blue {
  @include button-blue(100px, 35px, 20px 15px 0 0);
  @include pre200(14px, whitesmoke);
}

.button-gray {
  @include button-gray(100px, 35px, 20px 10px 0 0);
  @include pre200(14px, $black);
}

.input {
  @include input(280px, 32px, 18px, 0 10px, 8px);
}

.font_title {
  @include pre300(30px, $black);
  margin: 30px 0 20px 0;
}

.font-label {
  @include pre100(16px, $black);
  margin: 10px 0 10px 0;
}
</style>
