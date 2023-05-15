import axios from 'axios';

import useSWR from 'swr';

const BASE_URL = 'http://localhost:8080';

const fetcher = (path) => axios
  .get(`${BASE_URL}/${path}`)
  .then((response) => response.data);

export default function useFetchCart() {
  const { data, mutate } = useSWR('cart-line-items', fetcher);

  const { lineItems } = data || {};

  return {
    lineItems,
    reload: mutate,
  };
}
