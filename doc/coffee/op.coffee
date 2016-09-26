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
  $('p').click (e) ->
    mlog("click........#{e.timeStamp}........" + (e.timeStamp - window.lastClickTime))
    # console.log "click....."
    dt = e.timeStamp - window.lastClickTime
    window.lastClickTime = e.timeStamp
    return if(dt < 100)
    console.dir $(e.target)
    if $(e.target)[0].tagName == 'P'
      p = $(e.target)
    else
      p = $(e.target).parents('p')
    console.dir p
    $(p).addClass("select")
    # $(p).toggleClass("select")

$(document).ready ()-> onPageLoaded()

onerror = (e) ->
  alert("JS 发生错误！")
