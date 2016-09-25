onPageLoaded = () ->
  #  app log
  try
    if(typeof(jsObj) != "undefined")
      window.mlog = (msg) ->
        jsObj.log(msg)
    else
      window.mlog = (msg) ->
        console.log msg
    mlog('mlog has initialize...')

    initView()
  catch error
    alert("JS报错：#{error.stack}")
    initView()

initView = ()->
  $('p').each (num, tr) ->
    $(tr).mousemove (e) =>
      $(tr).addClass('select') if e.ctrlKey
      $(tr).removeClass('select') if e.altKey
      # updateStats() if e.ctrlKey || e.altKey
