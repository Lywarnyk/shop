function addParamToQueryStringParameter(url, param, newValue) {
    const re = new RegExp("([?&])" + param + "=.*?(&|$)", "i");
    const separator = url.indexOf('?') !== -1 ? "&" : "?";
    if (url.match(re)) {
        return url.replace(re, '$1' + param + "=" + newValue + '$2');
    }
    else {
        return url + separator + param + "=" + newValue;
    }
}

function update(fieldName, fieldValue) {
    if (fieldValue && fieldName) {
        const url = addParamToQueryStringParameter(window.location.href, 'page', '1');
        const newUrl = addParamToQueryStringParameter(url, fieldName, fieldValue);
        if (newUrl) {
            window.location = newUrl;
        }
    }
}

$('#pref-perpage').on('change', function () {
    update($(this).attr('name'), $(this).val());
});

$('#sort').on('change', function () {
    update($(this).attr('name'), $(this).val());
});

$('#order').on('change', function () {
    update($(this).attr('name'), $(this).val());
});

function changePage(page) {
    window.location = addParamToQueryStringParameter(window.location.href, 'page', page);
}

var itemCount = 0;

$('.add').click(function (){
  itemCount ++;
  $('#itemCount').html(itemCount).css('display', 'block');
});

$('.clear').click(function() {
  itemCount = 0;
  $('#itemCount').html('').css('display', 'none');
  $('#cartItems').html('');
});
