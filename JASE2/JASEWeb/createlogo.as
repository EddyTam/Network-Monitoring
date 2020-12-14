package {
	import flash.display.DisplayObjectContainer;
	import flash.display.MovieClip;
	import flash.display.Stage;
	import flash.display.StageAlign;
	import flash.display.StageScaleMode;
	import flash.system.LoaderContext;
	import flash.net.URLLoader;
	import flash.net.URLRequest;
	import flash.display.Loader;
	import flash.events.Event;
	import flash.events.IOErrorEvent;
	import flash.events.MouseEvent;
	import flash.net.navigateToURL;
	import org.libspark.betweenas3.BetweenAS3;
	import org.libspark.betweenas3.tweens.ITween;
	import org.libspark.betweenas3.events.TweenEvent;
	import org.libspark.betweenas3.easing.Expo;
	import org.libspark.betweenas3.easing.Sine;

	public class createlogo extends MovieClip{

		private var _stage:Stage;
		private var _root:MovieClip;
		private var logoLoader:Loader = new Loader();
		private var logoLoaderContext:LoaderContext = new LoaderContext(true);
		
		private var logomc:MovieClip = new MovieClip();

		//
		//CONSTRUCTOR
		//
		
		function createlogo(st:Stage, r:MovieClip) {
			_stage = st;
			_root = r;
			logoLoader.contentLoaderInfo.addEventListener(IOErrorEvent.IO_ERROR, function(evt:IOErrorEvent):void {},false,0,true);
			logoLoader.load(new URLRequest(_root.logourl), logoLoaderContext);
			logoLoader.contentLoaderInfo.addEventListener(Event.COMPLETE, logoLoadingDone);
		}
		
		//
		//LOGO LOADING
		//
		
		private function logoLoadingDone(e:Event):void {
			logomc.alpha = 0;
			BetweenAS3.apply(logomc, {_blurFilter: {blurX: 5,blurY: 5, quality:1}});
			_stage.addChildAt(logomc, 3);
			logomc.addChild(logoLoader.content)
			
			var t1:ITween = BetweenAS3.to(logomc,{alpha:1},1,Expo.easeOut);
			var d1:ITween = BetweenAS3.delay(t1, 0.5);
			var t2:ITween = BetweenAS3.to(logomc,{_blurFilter:{blurX: 0, blurY: 0, quality:1}},1.5,Expo.easeOut);
			var d2:ITween = BetweenAS3.delay(t2, 0.5);
			var p:ITween = BetweenAS3.parallel(t1,d1,t2,d2);
			p.play();
			
			_stage.addEventListener(Event.ADDED, onStageResize,false,0,true);
			_stage.addEventListener(Event.RESIZE, onStageResize);
			logomc.mouseEnabled = true;
			logomc.buttonMode = true;
			logomc.addEventListener(MouseEvent.CLICK, mouse_click,false,0,true);
			onStageResize(null);
		}
		
		//
		//LOGO CLICK
		//
		
		private function mouse_click(event):void {
			var urllink:String = _root.logoclicklink;
			var urltarget:String = _root.logoclicktarget;
			var req:URLRequest = new URLRequest(urllink);
			try {
			  navigateToURL(req, urltarget); 
			} catch (e:Error) {
			  trace("Could not navigate to URL!");
			}
		}
		
		//
		//STAGE RESIZE FUNCTION
		//
		
		private function onStageResize(e:Event):void {
			logomc.x = Math.round(_stage.stageWidth/2)-Math.round(_root.templatewidth/2)+_root.logopadding;
			//logoLoader.content.x = _root.logopadding;
			logomc.y = Math.round(_root.headerheight/2)-Math.round(_root.logoheight/2);
		}
	}
}