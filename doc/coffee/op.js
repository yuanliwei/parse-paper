var initView, onPageLoaded, onerror;

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
  return $('p').click(function(e) {
    var dt, p;
    mlog(("click........" + e.timeStamp + "........") + (e.timeStamp - window.lastClickTime));
    dt = e.timeStamp - window.lastClickTime;
    window.lastClickTime = e.timeStamp;
    if (dt < 100) {
      return;
    }
    console.dir($(e.target));
    if ($(e.target)[0].tagName === 'P') {
      p = $(e.target);
    } else {
      p = $(e.target).parents('p');
    }
    console.dir(p);
    return $(p).addClass("select");
  });
};

$(document).ready(function() {
  return onPageLoaded();
});

onerror = function(e) {
  return alert("JS 发生错误！");
};
