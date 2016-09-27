var addIdForAllParagraphs, addTypeForSel, getAllSels, initJsObj, initView, onPageLoaded, onerror;

onPageLoaded = function() {
  var error, error1;
  try {
    if (typeof jsObj === "undefined") {
      initJsObj();
    }
    window.mlog = function(msg) {
      return jsObj.log(msg);
    };
    mlog('mlog has initialize...');
    return initView();
  } catch (error1) {
    error = error1;
    alert("JS报错：" + error.stack);
    return initView();
  }
};

addTypeForSel = function(type) {
  var ids;
  ids = [];
  $('.paragraphs.select').each((function(_this) {
    return function(i, e) {
      var index, j;
      for (index = j = 0; j <= 20; index = ++j) {
        e.classList.remove("paragraphsType_" + index);
      }
      e.classList.add("paragraphsType_" + type);
      return ids.push(e.id.split('_')[1]);
    };
  })(this));
  ids.join(',');
  return jsObj.setParagraphsType(type, ids);
};

getAllSels = function() {
  var ids;
  ids = [];
  $('.paragraphs.select').each(function(i, e) {
    return ids.push(e.id.split('_')[1]);
  });
  return ids.join(',');
};

initView = function() {
  addIdForAllParagraphs();
  return $('p').click(function(e) {
    var dt, p;
    console.log("click.....");
    dt = Date.now() - window.lastClickTime;
    window.lastClickTime = Date.now();
    if (dt < 100) {
      return;
    }
    if ($(e.target)[0].tagName === 'P') {
      p = $(e.target);
    } else {
      p = $(e.target).parents('p');
    }
    return $(p).toggleClass("select");
  });
};

$(document).ready(function() {
  return onPageLoaded();
});

addIdForAllParagraphs = function() {
  var c, d;
  d = $('div.container');
  c = d.children();
  return c.each(function(index, tag) {
    tag.id = "paragraphs_" + index;
    return tag.classList.add("paragraphs");
  });
};

onerror = function(e) {
  return alert("JS 发生错误！");
};

initJsObj = function() {
  window.jsObj = {};
  jsObj.log = function(msg) {
    return console.log(msg);
  };
  return jsObj.setParagraphsType = function(type, indexStr) {
    return console.log("setParagraphsType type : " + type + " " + indexStr);
  };
};
