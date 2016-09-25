var initView, onPageLoaded;

onPageLoaded = function() {
  var error, error1;
  try {
    if (typeof jsObj !== "undefined") {
      window.mlog = function(msg) {
        return jsObj.log(msg);
      };
    } else {
      window.mlog = function(msg) {
        return console.log(msg);
      };
    }
    mlog('mlog has initialize...');
    return initView();
  } catch (error1) {
    error = error1;
    alert("JS报错：" + error.stack);
    return initView();
  }
};

initView = function() {
  return $('p').each(function(num, tr) {
    return $(tr).mousemove((function(_this) {
      return function(e) {
        if (e.ctrlKey) {
          $(tr).addClass('select');
        }
        if (e.altKey) {
          return $(tr).removeClass('select');
        }
      };
    })(this));
  });
};
