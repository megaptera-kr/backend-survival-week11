// in this file you can append custom step methods to 'I' object

const API_BASE_URL = 'http://localhost:8080';

module.exports = () => actor({
  setUp() {
    const I = this;

    I.amOnPage(`${API_BASE_URL}/backdoor/setup-database`);

    I.wait(5);
  }
});
