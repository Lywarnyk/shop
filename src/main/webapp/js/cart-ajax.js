function addProductToCart(id){
    $.post("cart",
    {
        transportId: id
    },
  setAmount)};
function setAmount(cart){
   $('#itemCount').text(cart.transportsAmount);
}
function updateAmount(id) {
  var amount = $("#" + id).val();
  if(amount == 0){
   remove(id);
 } else {
  $.ajax({
      url: 'cart',
      type: 'PUT',
      data: JSON.stringify({"transportId" : id, "transportAmount" :  amount}),
       //body: {"transportId" : id, "transportAmount" :  amount},
      success: function (cart) {
        $("#totalPrice").text('Total: $' + cart.totalPrice);
        setAmount(cart);
      }
  });
 }
};
function remove(id) {
  $.ajax({
      url: 'cart' + '?' +   "transportId=" + id,
      type: 'DELETE',
      success: function (cart) {
        $("#totalPrice").text('Total: $' + cart.totalPrice);
        setAmount(cart);
        $("#orderline" + id).remove();
      }
  });
};
function showCardBlock() {
  var value = $("#payType").val();
  if(value === 'card'){
   $('#cardNumber').show();
 }else {
   $('#cardNumber').hide();
 }
};
