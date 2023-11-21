<script setup lang="ts">
import { ref } from 'vue';

const startDate = ref('');
const endDate = ref('');
const emits = defineEmits(['update-date']);

function submitDate() {
  emits('update-date', startDate.value, endDate.value);
}
</script>

<template>
  <div class="container-col">
    <form class="content-col">
      <div class="form-col">
        <div class="font_title">조회 기간 선택하기</div>
        <div class="font-label">시작날짜와 끝 날짜를 선택해주세요.</div>
        <input
          class="input"
          type="date"
          v-model="startDate"
          style="width: 180px"
        />
        <input
          class="input"
          type="date"
          v-model="endDate"
          style="width: 180px"
        />
        <div class="content-row">
          <div type="submit" class="button-blue" @click="submitDate">
            선택완료
          </div>
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
  margin-bottom: 50px;
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
  margin-bottom: 10px;
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
