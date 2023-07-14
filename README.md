# aswemake
애즈위메이크 백엔드 과제

## 프로젝트 실행 방법
1. 프로젝트 실행 후 http://localhost:8080/swagger-ui.html 접속.
   
2. product-controller에 postProduct(상품 생성) 실행. (테스트 하기 편한 만큼 생성)
   - postProduct : {"price": 1000,"productName": "water"}
  
3. product-controller에 getProducts(모든 상품 조회), getProduct(특정 상품 조회), deleteProduct(상품 삭제), patchProduct(상품 수정), getProductPriceAtTime(특정 시점의 상품 가격 조회) 실행.
   - getProduct : (product-id : 1)
   - deleteProduct : (product-id : 1)
   - patchProduct : (product-id : 1), {"price": 1100,"productName": "water"}
   - getProductPriceAtTime : (productName : water), (time : 2023-07-14 09:00:00)

4. order-controller에 postOrder(주문 생성) 실행.
   - postOrder : {"basket" : [ {"productId": 1,"quantity": 3}, {"productId": 3,"quantity": 2} ], "deliveryFee": 5000}

5. order-controller에 totalPrice(주문에 대한 총 금액) 실행.
   - totalPrice : (order-id : 1)

6. coupon-controller에 postCoupon(쿠폰 생성) 실행.
   - type에는 "고정", "비율" 둘 중 하나만 삽입하기.
   - range에는 "주문 전체", "특정 상품 한정" 둘 중 하나만 삽입하기.
   - value는 type이 "고정"이라면 예를들어 3000원 할인이면 3000 삽입, type이 "비율" 이라면 예를들어 30% 할인이면 0.7 삽입
   - productId는 type이 "비율"이고, range가 "특정 상품 한정"일때 할인 받고싶은 producId 삽입
   - postCoupon : {"productId": 1,"range": "특정 상품 한정","type": "비율","value": 0.7} or {"productId": 0,"range": "주문 전체","type": "비율","value": 0.7} or {"productId": 0,"range": "주문 전채","type": "고정","value": 5000}
   - 위에 보여준 예시 3개로 하는게 편하다.

7. order-controller에 couponApplicationPrice(쿠폰을 적용한 주문에 필요 금액) 실행.
   - couponApplicationPrice : (orderId : 1), (couponId : 1)
   - 6번에서 말한 예시쿠폰 3개를 다 해보며 가격 비교
