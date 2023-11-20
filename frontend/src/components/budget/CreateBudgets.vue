<script setup lang="ts">
import { ref } from 'vue';
import { library } from '@fortawesome/fontawesome-svg-core';
import { faCirclePlus } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { createBudgets } from '../../api/BudgetApi.ts';
import router from '../../router';

library.add(faCirclePlus);

const categoryPercentagePairs = ref([{ category: '', percentage: '' }]);

const addCategoryPercentagePair = () => {
  categoryPercentagePairs.value.push({ category: '', percentage: '' });
};

const handleBudgetSubmit = async () => {
  try {
    const budgetsData = categoryPercentagePairs.value.map(pair => ({
      category: pair.category,
      percentage: parseInt(pair.percentage),
    }));

    const response = await createBudgets(budgetsData);
    if (response.status === 200) {
      await router.push('/budgets/me');
    }
  } catch (error) {
    console.error(error);
  }
};
</script>

<template>
  <div class="container-col">
    <form class="content-col">
      <div class="form-col">
        <div class="font_title">예산 설정 하기</div>
        <div class="font-label">카테고리와 비율을 입력해주세요.</div>
        <div
          class="select-box"
          v-for="(pair, index) in categoryPercentagePairs"
          :key="index"
        >
          <select
            v-model="pair.category"
            class="input"
            style="width: 105px; margin-right: 10px"
          >
            <option>식비</option>
            <option>청약</option>
            <option>보험비</option>
            <option>가스비</option>
            <option>유류비</option>
            <option>교육비</option>
            <option>경조사</option>
            <option>통신비</option>
            <option>교통비</option>
            <option>자기개발</option>
            <option>주거비</option>
            <option>미용비</option>
            <option>용돈</option>
            <option>관리비</option>
            <option>적금</option>
            <option>문화생활</option>
            <option>병원비</option>
            <option>모임비</option>
          </select>
          <input
            v-model="pair.percentage"
            class="input"
            type="number"
            style="width: 50px; margin-right: 10px"
          />
        </div>
        <div class="content-row">
          <button
            @click.prevent="addCategoryPercentagePair"
            class="button-gray"
          >
            <font-awesome-icon
              icon="fa-solid fa-circle-plus"
              style="margin-right: 5px"
            />
            추가하기
          </button>
          <button
            @click.prevent="handleBudgetSubmit"
            type="submit"
            class="button-blue"
          >
            등록
          </button>
        </div>
      </div>
    </form>
  </div>
</template>

<style scoped lang="scss">
@import '../../assets/styles/common/container';
@import '../../assets/styles/common/button';
@import '../../assets/styles/common/input';
@import '../../assets/styles/color/_color';
@import '../../assets/styles/common/typho';

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
  margin: 10px 0 7px 0;
}
</style>
