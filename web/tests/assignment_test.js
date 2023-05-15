Feature('과제 테스트');

Scenario('쇼핑몰 제품 등록하기', ({ I }) => {
  I.setUp();

  I.amOnPage('/');

  I.click('관리자');

  I.see('등록된 제품이 없습니다');

  I.fillField('제품 이름', '메가테라 굿즈');
  I.fillField('가격', '10000');
  I.attachFile('제품 이미지', './tests/fixtures/files/test-sample.jpg');

  I.click('제품 등록하기');

  I.waitForText('메가테라 굿즈');
});

Scenario('장바구니 담기', ({ I }) => {
  I.amOnPage('/');

  I.click('쇼핑몰');

  I.waitForText('제품목록');

  I.click('메가테라 굿즈');

  I.waitForText('메가테라 굿즈(10,000원)');

  I.see('수량: 1개');

  I.click('+');

  I.waitForText('수량: 2개');

  I.click('-');

  I.waitForText('수량: 1개');

  I.click('X');

  I.dontSee('메가테라 굿즈(10,000원)');

  I.click('메가테라 굿즈');

  I.waitForText('총 금액: 10,000원');
});
