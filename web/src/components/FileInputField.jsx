import styled from 'styled-components';
import { useEffect, useRef } from 'react';

const Container = styled.div`
  margin-bottom: 1rem;
  padding-block: .5rem;

  label {
    display: block;
  }
`;

export default function FileInputField({ label, value, onChange }) {
  const ref = useRef('');

  const id = `file-input-${label}`;

  useEffect(() => {
    if (!value) {
      ref.current.value = '';
    }
  }, [value]);

  const handleChange = (event) => {
    const { target } = event;

    onChange({
      key: target.name,
      value: target.files[0],
    });
  };

  return (
    <Container>
      <label htmlFor={id}>{label}</label>
      <input
        ref={ref}
        type="file"
        id={id}
        name="image"
        accept=".jpg, .jpeg, .png"
        onChange={handleChange}
      />
    </Container>
  );
}
