<script setup lang="ts">
import { ref } from 'vue';
import SelectDate from './search/SelectDate.vue';
import SelectSearchType from './search/SelectSearchType.vue';
import SelectCategory from './search/SelectCategory.vue';
import SelectMoneyRange from './search/selectMoneyRange.vue';
import {
  normalSearch,
  searchWithCategory,
  searchWithMoneyRange,
} from '../../api/ExpenditureApi.ts';
import SearchResult from './search/SearchResult.vue';

const currentStep = ref(1);
const searchType = ref('general');
const startDate = ref('');
const endDate = ref('');
const selectedCategory = ref('');
const moneyRange = ref({ min: 0, max: 0 });
const searchResults = ref(null);

function handleUpdateDate(sDate, eDate) {
  startDate.value = sDate;
  endDate.value = eDate;
  currentStep.value = 2;
}

function handleUpdateType(type) {
  searchType.value = type;
  if (type === 'general') {
    submitSearch();
  } else {
    currentStep.value = type === 'category' ? 3 : 4;
  }
}

function handleUpdateCategory(category) {
  selectedCategory.value = category;
  submitSearch();
}

function handleUpdateRange(range) {
  moneyRange.value = range;
  submitSearch();
}

async function submitSearch() {
  try {
    let response;
    const searchData = {
      startDate: startDate.value,
      endDate: endDate.value,
    };

    if (searchType.value == 'category') {
      response = await searchWithCategory({
        ...searchData,
        category: selectedCategory.value,
      });

      if (response.status == 200) {
        currentStep.value = 5;
      }
    } else if (searchType.value == 'range') {
      response = await searchWithMoneyRange({
        ...searchData,
        minMoney: moneyRange.value.min,
        maxMoney: moneyRange.value.max,
      });

      if (response.status == 200) {
        currentStep.value = 5;
      }
    } else if (searchType.value == 'general') {
      response = await normalSearch(searchData);
      if (response.status == 200) {
        currentStep.value = 5;
      }
    }

    searchResults.value = response.data;
    console.log(response.data);
  } catch (error) {
    console.error(error);
  }
}
</script>

<template>
  <div class="container-col">
    <SelectDate v-if="currentStep === 1" @update-date="handleUpdateDate" />
    <SelectSearchType
      v-if="currentStep === 2"
      @update-type="handleUpdateType"
    />
    <SelectCategory
      v-if="currentStep === 3 && searchType === 'category'"
      @update-category="handleUpdateCategory"
    />
    <SelectMoneyRange
      v-if="currentStep === 4 && searchType === 'range'"
      @update-range="handleUpdateRange"
    />
    <SearchResult
      v-if="searchResults && currentStep == 5"
      :results="searchResults"
    />
  </div>
</template>

<style lang="scss">
@import '../../assets/styles/common/container';
@import '../../assets/styles/color/color';

.container-col {
  @include container(column, flex-start, flex-start, 100%, auto);
}

.content-col {
  @include container(column, center, center, 100%, auto);
}
</style>
