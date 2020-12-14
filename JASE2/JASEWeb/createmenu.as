package {
	import flash.display.DisplayObjectContainer;
	import flash.display.MovieClip;
	import flash.display.Stage;
	import flash.display.StageAlign;
	import flash.display.StageScaleMode;
	import flash.display.GradientType;
	import flash.display.SpreadMethod;
	import flash.display.InterpolationMethod;
	import flash.display.Sprite;
	import flash.geom.Matrix;
	import flash.display.Bitmap;
	import flash.display.BitmapData;
	import flash.net.URLLoader;
	import flash.net.URLRequest;
	import flash.display.Loader;
	import flash.system.LoaderContext;
	import flash.events.Event;
	import flash.events.IOErrorEvent;
	import flash.events.TimerEvent;
	import flash.utils.Timer;
	import org.libspark.betweenas3.BetweenAS3;
	import org.libspark.betweenas3.tweens.ITween;
	import org.libspark.betweenas3.events.TweenEvent;
	import org.libspark.betweenas3.easing.Expo;
	import org.libspark.betweenas3.easing.Sine;

	public class createmenu extends MovieClip {

		private var _stage:Stage;
		private var _root:MovieClip;

		public var menuy:int = 0;
		public var mainmenufadedin:int = 0;
		public var mainmenu_array:Array = new Array();
		public var slideoutmenu:MovieClip = null;
		public var selectedmenu:MovieClip = null;
		
		private var timeout:int = 500;
		private var changeTimer:Timer;
		
		private var t_menu:ITween;

		//
		//CONSTRUCTOR
		//

		function createmenu(st:Stage, r:MovieClip) {
			_stage = st;
			_root = r;

			_stage.addChild(this);
			_stage.addEventListener(Event.ADDED, onStageResize,false,0,true);
			_stage.addEventListener(Event.RESIZE, onStageResize);
			
			addEventListener(Event.ENTER_FRAME, tweenCheck);

			for (var i:int = 0; i < _root.mainmenulength; i++) {
				this.addChild(new mainmenu(_root, this, i));
			}
			
			SWFAddress.addEventListener(SWFAddressEvent.CHANGE, handleSWFAddress);
		}
			
		function tweenCheck(e:Event):void {
			if(mainmenufadedin == _root.mainmenulength){
				removeEventListener(Event.ENTER_FRAME, tweenCheck);
				_root.firstrun = false;
			}
		}
		
		//
		//STAGE RESIZE FUNCTION
		//

		private function onStageResize(e:Event):void {
			this.x = Math.round(_stage.stageWidth/2)-Math.round(_root.templatewidth/2)+_root.menuleftpadding;
			
			if((_stage.stageHeight-_root.templateheight-_root.headerminheight)>=_root.headerminheight && (_stage.stageHeight-_root.templateheight-_root.headerminheight)<=_root.headermaxheight){
				_root.headerheight = Math.round(_stage.stageHeight-_root.templateheight-_root.headerminheight);
			}else if((_stage.stageHeight-_root.templateheight-_root.headerminheight)<_root.headerminheight){
				_root.headerheight = _root.headerminheight;
			}else if((_stage.stageHeight-_root.templateheight-_root.headerminheight)>_root.headermaxheight){
				_root.headerheight = _root.headermaxheight;
			}
			this.y = _root.headerheight;
		}
		
		//
		//COLOR CONVERTER HEX TO RGB
		//

		public function makeRGB(hex:Number):Object {
			var rgb:Object = {
			  red: ((hex & 0xFF0000) >> 16),
			  green: ((hex & 0x00FF00) >> 8),
			  blue: ((hex & 0x0000FF))
			};
			return rgb;
		}
		
		private function tweenDone(target:MovieClip, tweentarget:int):void{
			if(!_root.firstrun){
				target.y = tweentarget;
				target.alpha = 1;
			}
		}
		
		//
		//SLIDE DOWN MAIN MENU
		//
		
		public function slideDownMenu(target:MovieClip):void {
			for (var i:int = target._ivar+1; i < mainmenu_array.length; i++) {
				var tweentarget:int = mainmenu_array[i].menuysave+target.submenuheight+Math.abs(_root.submenuspacing);
				if(mainmenu_array[i].t_move!=null){
					mainmenu_array[i].t_move.stop();
				}
				if(!_root.firstrun){
					mainmenu_array[i]._menumc.alpha = 0.99;
				}
				mainmenu_array[i].t_move = BetweenAS3.to(mainmenu_array[i]._menumc,{y:tweentarget},0.5,Expo.easeOut);
				mainmenu_array[i].t_move.onComplete = tweenDone;
				mainmenu_array[i].t_move.onCompleteParams = [mainmenu_array[i]._menumc, tweentarget];
				mainmenu_array[i].t_move.play();
				mainmenu_array[i].slidedown = true;
			}
		}
		
		//
		//SLIDE UP MAIN MENU
		//
		
		public function slideUpMenu(target:MovieClip):void {
			for (var i:int = target._ivar+1; i < mainmenu_array.length; i++) {
				var tweentarget:int = mainmenu_array[i].menuysave;
				if(mainmenu_array[i].t_move!=null){
					mainmenu_array[i].t_move.stop();
				}
				if(!_root.firstrun){
					mainmenu_array[i]._menumc.alpha = 0.99;
				}
				mainmenu_array[i].t_move = BetweenAS3.to(mainmenu_array[i]._menumc,{y:tweentarget},0.5,Expo.easeInOut);
				mainmenu_array[i].t_move.onComplete = tweenDone;
				mainmenu_array[i].t_move.onCompleteParams = [mainmenu_array[i]._menumc, tweentarget];
				mainmenu_array[i].t_move.play();
				mainmenu_array[i].slidedown = false;
			}
		}
		
		//
		//SHOW SUBMENU
		//
		
		public function showSubMenu(target:MovieClip):void {
			for (var i:int = 0; i < target.submenu_array.length; i++) {
				target.submenu_array[i].fadeIn(i);
			}
		}
		
		//
		//HIDE SUBMENU
		//
		
		public function hideSubMenu():void {
			for (var i:int = slideoutmenu.submenu_array.length-1; i > -1; i--) {
				slideoutmenu.submenu_array[i].fadeOut(slideoutmenu.submenu_array.length-i);
			}
		}
		
		//
		//MODULE SELECTOR
		//
		
		public function selectModule():void {
			
			if(changeTimer!=null){
				changeTimer.stop();
				if(changeTimer.hasEventListener(TimerEvent.TIMER_COMPLETE)){
					changeTimer.removeEventListener(TimerEvent.TIMER_COMPLETE, proceed);
				}
				changeTimer = null;
			}
			
			changeTimer = new Timer(timeout, 1);
			changeTimer.addEventListener(TimerEvent.TIMER_COMPLETE, proceed);
			changeTimer.start();
		}
		
		private function proceed(e:Event):void {
			if(changeTimer.hasEventListener(TimerEvent.TIMER_COMPLETE)){
				changeTimer.removeEventListener(TimerEvent.TIMER_COMPLETE, proceed);
			}
			changeTimer = null;
				
			SWFAddress.setValue(selectedmenu.deeplink);
			_root.module.loadModule(this, selectedmenu.modulepath, selectedmenu.modulexmlpath, selectedmenu.modulewidth, selectedmenu.moduleheight);
		}
		
		//
		//DEEP LINKING HANDLER
		//
		
		public function handleSWFAddress(e:SWFAddressEvent) {
			if(SWFAddress.getValue().split("/")[1]!=""){
				for (var i:int = 0; i < mainmenu_array.length; i++) {
					
					if (mainmenu_array[i].deeplink == SWFAddress.getValue().split("/")[1]) {
						mainmenu_array[i].mouse_over(null);
						mainmenu_array[i].mouse_click(null);
						SWFAddress.setTitle(_root.websitetitle+" : "+_root.menudata_array[i]["headline"]);
						return;
					}
					
					for (var j:int = 0; j < mainmenu_array[i].submenu_array.length; j++) {
						
						if (mainmenu_array[i].submenu_array[j].deeplink == SWFAddress.getValue().split("/")[1]) {
							slideoutmenu = mainmenu_array[i];
							slideDownMenu(mainmenu_array[i]);
							showSubMenu(mainmenu_array[i]);
							mainmenu_array[i].submenu_array[j].mouse_over(null);
							mainmenu_array[i].submenu_array[j].mouse_click(null);
							SWFAddress.setTitle(_root.websitetitle+" : "+_root.menudata_array[i]["smheadline"][j]);
							return;
						}
						
					}
				}
			}else{
				if(selectedmenu==null){
					mainmenu_array[0].mouse_over(null);
					mainmenu_array[0].mouse_click(null);
					SWFAddress.setTitle(_root.websitetitle+" : "+_root.menudata_array[0]["headline"]);
				}
			}
		}
	}
}