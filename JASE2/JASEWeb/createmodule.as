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
	import flash.display.LoaderInfo;
	import flash.geom.Matrix;
	import flash.display.Bitmap;
	import flash.display.BitmapData;
	import flash.net.URLLoader;
	import flash.net.URLRequest;
	import flash.display.Loader;
	import flash.system.LoaderContext;
	import flash.events.Event;
	import flash.events.IOErrorEvent;
	import flash.events.ProgressEvent;
	import flash.events.IEventDispatcher;
	import org.libspark.betweenas3.BetweenAS3;
	import org.libspark.betweenas3.tweens.ITween;
	import org.libspark.betweenas3.events.TweenEvent;
	import org.libspark.betweenas3.easing.Expo;
	import org.libspark.betweenas3.easing.Sine;

	public class createmodule extends MovieClip {

		private var _stage:Stage;
		private var _root:MovieClip;
		private var _menu:MovieClip;

		public var modulecontainer:MovieClip = new MovieClip();
		public var modulexpos:int = 0;
		public var moduleypos:int = 0;
		
		private var module_link:String;
		private var module_xml:String;
		private var modulew:int;
		private var moduleh:int;
		
		private var loader:Loader;
		private var moduleURL:URLRequest;
		
		private var loadedModule:MovieClip;
		private var loadedModuleObj:Object;
		private var preloadervar:MovieClip;
		
		private var moduleMask:Sprite;
		private var t_maskshow:ITween;
		private var t_maskhide:ITween;
		private var t_preloader:ITween;
		
		private var loaded:Boolean = false;

		//
		//CONSTRUCTOR
		//

		function createmodule(st:Stage, r:MovieClip) {
			_stage = st;
			_root = r;
			
			preloadervar = new preloader();
			preloadervar.alpha = 0;
			preloadervar.x = Math.round(_stage.stageWidth)-25;
			preloadervar.y = 25;
			_stage.addChild(preloadervar);
			
			_stage.addChildAt(modulecontainer, 4);
			
			moduleMask = new Sprite();
			moduleMask.graphics.beginFill(0x000000);
			moduleMask.graphics.drawRect(0, 0, 10, 10);
			moduleMask.graphics.endFill();
			moduleMask.mouseEnabled = false;
			moduleMask.alpha = 0;
			moduleMask.width = 0;
			moduleMask.height = 0;
			
			_stage.addChild(moduleMask);
			
			_stage.addEventListener(Event.RESIZE, onStageResize);
		}
		
		//
		//TEMPLATE MODULE LOADER
		//
		
		public function loadModule(cmenu:MovieClip, moduleLink:String, moduleXML:String, moduleWidth:int, moduleHeight:int):void {
			_menu = cmenu;
			module_link = moduleLink;
			module_xml = moduleXML;
			modulew = moduleWidth;
			moduleh = moduleHeight;
			
			loaded = false;
			
			if(_root.activeimage!=_menu.selectedmenu.imagepath){
				_root.activeimage = _menu.selectedmenu.imagepath;
				_root.bg.initImage();
			}
						
			if(loadedModule==null){
				initLoading();
			}else{
				if(t_maskhide!=null){
					t_maskhide.stop();
					unloadModule();
				}else if(t_maskshow!=null){
					t_maskshow.stop();
					unloadModule();
				}else{
					hideModule();
				}
			}
		}
		
		private function initLoading():void {
			showPreloader();

			var loadtarget:URLRequest = new URLRequest();
			loadtarget.url = module_link;
			
			if(loader!=null && loader.contentLoaderInfo.hasEventListener(Event.COMPLETE)){
				loader.contentLoaderInfo.removeEventListener(Event.COMPLETE, addModule);
			}
			
			if(loader!=null){
				loader = null;
			}
			
			loader = new Loader();
			
			addListeners(loader.contentLoaderInfo);
			loader.load(loadtarget);
		}
		
		private function addListeners(dispatcher:IEventDispatcher):void {
			dispatcher.addEventListener(Event.COMPLETE, addModule, false,0,true);
		}
		
		private function addModule(event:Event):void {
			hidePreloader();
			onStageResize(null);
			event.target.removeEventListener(Event.COMPLETE, addModule);
			loadedModule = event.target.content;
			moduleMask.height = 0;
			moduleMask.width = modulew;
			loadedModule.mask = moduleMask;
			modulecontainer.addChild(loadedModule);
			loadedModuleObj = Object(loadedModule);
			loadedModuleObj.setVar(module_xml, _root);
			loadedModuleObj.setPos(modulecontainer.x, modulecontainer.y);
			loaded = true;
			showModule();
		}
		
		private function unloadModule():void{
			loader.unloadAndStop();
			t_maskhide = null;
			loadedModule.mask = null;
			modulecontainer.removeChild(loadedModule);
			while(modulecontainer.numChildren){  
				modulecontainer.removeChildAt(0);
			}
			loadedModule = null;
			loadedModuleObj = null;
			loaded = false;
			initLoading();
			
			if(_root.util.pausevar && !_root.util.activepausevar){
				_root.util.extPlay();
			}
		}
		
		public function deactivateMask():void{
			loadedModule.mask = null;
		}
		
		public function activateMask():void{
			loadedModule.mask = moduleMask;
		}
		
		private function showModule():void{
			if(t_maskhide!=null){
				t_maskhide.stop();
			}
			if(t_maskshow!=null){
				t_maskshow.stop();
			}
			t_maskshow = BetweenAS3.to(moduleMask,{height:moduleh},1,Expo.easeInOut);
			t_maskshow.onComplete = moduleShown;
			t_maskshow.play();
		}
		
		private function moduleShown():void{
			t_maskshow = null;
		}
		
		private function hideModule():void{
			loadedModule.mask = moduleMask;
			if(t_maskshow!=null){
				t_maskshow.stop();
			}
			if(t_maskhide!=null){
				t_maskhide.stop();
			}
			t_maskhide = BetweenAS3.to(moduleMask,{height:0},1,Expo.easeInOut);
			t_maskhide.onComplete = unloadModule;
			t_maskhide.play();
		}
		
		private function showPreloader():void{
			if(t_preloader!=null){
				t_preloader.stop();
			}
			t_preloader = BetweenAS3.to(preloadervar,{alpha:1},0.9,Expo.easeOut);
			t_preloader.play();
		}
		
		private function hidePreloader():void{
			if(t_preloader!=null){
				t_preloader.stop();
			}
			preloadervar.alpha = 1;
			t_preloader = BetweenAS3.to(preloadervar,{alpha:0},0.9,Expo.easeIn);
			t_preloader.play();
		}
		
		//
		//STAGE RESIZE FUNCTION
		//

		private function onStageResize(e:Event):void {
			modulecontainer.x = moduleMask.x = Math.round(_stage.stageWidth/2)-Math.round(_root.templatewidth/2)+_root.menuareawidth;
			
			if((_stage.stageHeight-_root.templateheight-_root.headerminheight)>=_root.headerminheight && (_stage.stageHeight-_root.templateheight-_root.headerminheight)<=_root.headermaxheight){
				modulecontainer.y = moduleMask.y = Math.round(_stage.stageHeight-_root.templateheight-_root.headerminheight);
			}else if((_stage.stageHeight-_root.templateheight-_root.headerminheight)<_root.headerminheight){
				modulecontainer.y = moduleMask.y= _root.headerminheight;
			}else if((_stage.stageHeight-_root.templateheight-_root.headerminheight)>_root.headermaxheight){
				modulecontainer.y = moduleMask.y= _root.headermaxheight;
			}
			
			if(loaded){
				loadedModuleObj.setPos(modulecontainer.x, modulecontainer.y);
			}
			if(preloadervar!=null){
				preloadervar.x = Math.round(_stage.stageWidth)-25;
			}
		}
	}
}