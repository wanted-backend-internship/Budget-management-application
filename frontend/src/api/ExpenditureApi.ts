import axiosInstance from './interceptor/RequestInterceptor.ts';

export const createExpenditure = async (
  expenditureData: Array<{
    category: string;
    expenditureAmount: number;
    expenditureAt: string;
    memo: string;
  }>,
) => {
  const response = await axiosInstance.post('/expenditures', expenditureData);
  console.log(response.data);
  return response;
};

export const updateExpenditure = async (expenditureData: {
  category: string;
  expenditureAmount: number;
  expenditureAt: string;
  memo: string;
}) => {
  const response = await axiosInstance.patch('/expenditures', expenditureData);
  console.log(response.data);
  return response;
};

export const deleteExpenditure = async (expenditureId: number) => {
  await axiosInstance.delete('/expenditures/' + expenditureId);
};

export const expenditureDetail = async (expenditureId: number) => {
  const response = await axiosInstance.get('/expenditures/' + expenditureId);
  console.log(response.data);
  return response;
};

export const searchWithCategory = async (expenditureData: {
  startDate: string;
  endDate: string;
  category: string;
}) => {
  const response = await axiosInstance.post(
    '/expenditures/search/category',
    expenditureData,
  );
  console.log(response.data);
  return response;
};

export const searchWithMoneyRange = async (expenditureData: {
  startDate: string;
  endDate: string;
  minMoney: number;
  maxMoney: number;
}) => {
  const response = await axiosInstance.post(
    '/expenditures/search/money-range',
    expenditureData,
  );
  console.log(response.data);
  return response;
};

export const normalSearch = async (expenditureData: {
  startDate: string;
  endDate: string;
}) => {
  const response = await axiosInstance.post(
    '/expenditures/search',
    expenditureData,
  );
  console.log(response.data);
  return response;
};
