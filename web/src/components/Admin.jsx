import { useState } from 'react';

import styled from 'styled-components';

import useFetchProducts from '../hooks/useFetchProducts';
import useRegisterProduct from '../hooks/useRegisterProduct';

import Products from './Products';
import InputField from './InputField';
import FileInputField from './FileInputField';

const Container = styled.div`
  input::-webkit-outer-spin-button,
  input::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
  }

  input[type=number] {
    -moz-appearance: textfield;
  }
`;

export default function Admin() {
  const [product, setProduct] = useState({
    name: '',
    price: '',
    image: '',
  });

  const { products, reload } = useFetchProducts();

  const { registerProduct } = useRegisterProduct();

  const handleChangeProduct = ({ key, value }) => {
    setProduct({
      ...product,
      [key]: value,
    });
  };

  const handleRegisterProduct = async () => {
    const { name, price, image } = product;

    if (!name || !price || !image) {
      return;
    }

    const data = { name, price };

    await registerProduct({ data, image });

    setProduct(({
      ...product,
      name: '',
      price: '',
      image: '',
    }));

    await reload();
  };

  const handleClick = () => {
    // TODO: Delete this!
  };

  return (
    <Container>
      <h1>메가테라 쇼핑몰 관리자</h1>
      <div>
        <h2>제품 등록</h2>
        <InputField
          label="제품 이름"
          name="name"
          value={product.name}
          onChange={handleChangeProduct}
        />
        <InputField
          type="number"
          label="가격"
          name="price"
          value={product.price}
          onChange={handleChangeProduct}
        />
        <FileInputField
          label="제품 이미지"
          value={product.image}
          onChange={handleChangeProduct}
        />
      </div>
      <button type="button" onClick={handleRegisterProduct}>
        제품 등록하기
      </button>
      <Products products={products} onClick={handleClick}/>
    </Container>
  );
}
