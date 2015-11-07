package controllers

import play.api._
import play.api.mvc._

class ApplicationController() extends Controller {

  def index(video: Option[Int]) = Action { implicit request =>
    val videoUrl = video.flatMap { i =>
      Videos.lift(i-1)
    }

    Ok(views.html.index(videoUrl = videoUrl))
  }

  def videos() = Action { implicit request =>
    Ok(views.html.videos(Videos))
  }

  val Videos = Seq(
    "http://i.istockimg.com/video_passthrough/26091784/153/26091784.mp4",
    "http://i.istockimg.com/video_passthrough/22367433/153/22367433.mp4",
    "http://i.istockimg.com/video_passthrough/26091784/153/26091784.mp4",
    "http://i.istockimg.com/video_passthrough/22770823/153/22770823.mp4",
    "http://i.istockimg.com/video_passthrough/8932326/153/8932326.mp4",
    "http://i.istockimg.com/video_passthrough/11730641/153/11730641.mp4",
    "http://i.istockimg.com/video_passthrough/7221954/153/7221954.mp4",
    "http://i.istockimg.com/video_passthrough/18033539/153/18033539.mp4",
    "http://i.istockimg.com/video_passthrough/8402978/153/8402978.mp4",
    "http://i.istockimg.com/video_passthrough/3126853/153/3126853.mp4",
    "http://i.istockimg.com/video_passthrough/9428158/153/9428158.mp4",
    "http://i.istockimg.com/video_passthrough/9749078/153/9749078.mp4",
    "http://i.istockimg.com/video_passthrough/36348308/153/36348308.mp4",
    "http://i.istockimg.com/video_passthrough/15616858/153/15616858.mp4",
    "http://i.istockimg.com/video_passthrough/30074470/153/30074470.mp4",
    "http://i.istockimg.com/video_passthrough/17784128/153/17784128.mp4",
    "http://i.istockimg.com/video_passthrough/11858743/153/11858743.mp4",
    "http://i.istockimg.com/video_passthrough/9768624/153/9768624.mp4",
    "http://i.istockimg.com/video_passthrough/9936601/153/9936601.mp4",
    "http://i.istockimg.com/video_passthrough/38212644/153/38212644.mp4",
    "http://i.istockimg.com/video_passthrough/13905859/153/13905859.mp4",
    "http://i.istockimg.com/video_passthrough/5319079/153/5319079.mp4",
    "http://i.istockimg.com/video_passthrough/19964301/153/19964301.mp4",
    "http://i.istockimg.com/video_passthrough/19083883/153/19083883.mp4",
    "http://i.istockimg.com/video_passthrough/8543068/153/8543068.mp4",
    "http://i.istockimg.com/video_passthrough/19871300/153/19871300.mp4",
    "http://i.istockimg.com/video_passthrough/4869123/153/4869123.mp4",
    "http://i.istockimg.com/video_passthrough/4869123/153/4869123.mp4",
    "http://i.istockimg.com/video_passthrough/2866549/152/2866549.mp4",
    "http://i.istockimg.com/video_passthrough/2933965/153/2933965.mp4",
    "http://i.istockimg.com/video_passthrough/3357745/153/3357745.mp4",
    "http://i.istockimg.com/video_passthrough/3015694/152/3015694.mp4",
    "http://i.istockimg.com/video_passthrough/9580819/153/9580819.mp4",
    "http://i.istockimg.com/video_passthrough/8847667/153/8847667.mp4",
    "http://i.istockimg.com/video_passthrough/29183800/153/29183800.mp4",
    "http://i.istockimg.com/video_passthrough/13867294/153/13867294.mp4",
    "http://i.istockimg.com/video_passthrough/8737167/153/8737167.mp4",
    "http://i.istockimg.com/video_passthrough/8543068/153/8543068.mp4",
    "http://i.istockimg.com/video_passthrough/18401652/153/18401652.mp4",
    "http://i.istockimg.com/video_passthrough/28340934/153/28340934.mp4",
    "http://i.istockimg.com/video_passthrough/12306947/153/12306947.mp4",
    "http://i.istockimg.com/video_passthrough/13023577/153/13023577.mp4",
    "http://i.istockimg.com/video_passthrough/13358335/153/13358335.mp4"
  )

}
