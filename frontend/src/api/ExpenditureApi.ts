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
  const response = await axiosInstance.get('/budgets/' + expenditureId);
  console.log(response.data);
  return response;
};
