package{
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
	import flash.text.TextField;
	import flash.text.TextFormat;
	import flash.text.TextFieldAutoSize;
	import flash.text.AntiAliasType;
	import flash.text.GridFitType;
	import flash.system.LoaderContext;
	import flash.events.Event;
	import flash.events.IOErrorEvent;
	import org.libspark.betweenas3.BetweenAS3;
	import org.libspark.betweenas3.tweens.ITween;
	import org.libspark.betweenas3.events.TweenEvent;
	import org.libspark.betweenas3.easing.Expo;
	import org.libspark.betweenas3.easing.Quad;
	import org.libspark.betweenas3.easing.Quint;
	import org.libspark.betweenas3.easing.Sine;

	public class createfooter extends MovieClip{
		
		private var _stage:Stage;
		private var _root:MovieClip;
		
		private var footer:MovieClip = new MovieClip();
		
		private var solidbg:Sprite = new Sprite();
		private var pixel:Sprite = new Sprite();
		private var pixelbg:Sprite = new Sprite();
		private var pixeltile:BitmapData;
		
		private var txt:TextField = new TextField();
		
		private var t_footer:ITween;
		
		//
		//CONSTRUCTOR
		//
		
		function createfooter(st:Stage, r:MovieClip){
			_stage = st;
			_root = r;
			
			solidbg.graphics.beginFill(_root.footerbgcolor);
			solidbg.graphics.drawRect(0, 0, 5, 5);
			solidbg.graphics.endFill();
			solidbg.alpha = _root.footerbgopacity;

			pixel.graphics.beginFill(_root.footerpixelcolor, 1);
			pixel.graphics.drawRect(0, 0, 1, 1);
			pixel.graphics.drawRect(1, 1, 1, 1);
			pixel.graphics.endFill();
			pixeltile = new BitmapData(2, 2, true, _root.footerpixelcolor);
			pixeltile.draw(pixel);
			
			txt.multiline = true;
			txt.condenseWhite = true;
			txt.wordWrap = true;
			txt.embedFonts = true;
			txt.selectable = false;
			txt.antiAliasType = AntiAliasType.ADVANCED;
			txt.autoSize = TextFieldAutoSize.LEFT;
			txt.gridFitType = GridFitType.PIXEL;
			txt.width = _root.templatewidth;
			txt.styleSheet = _root.style;
			txt.htmlText = _root.footertext;
			var txt_shadow:dropshadow = new dropshadow(_root, txt, _root.footertextshadowdistance, 45, 0x000000, _root.footertextshadowopacity, _root.footertextshadowstrength, _root.footertextshadowstrength, 1, 1);
			
			footer.alpha = 0;
			footer.addChild(solidbg);
			footer.addChild(pixelbg);
			footer.addChild(txt);
			var this_shadow:dropshadow = new dropshadow(_root, footer, _root.footershadowdistance, 270, 0x000000, _root.footershadowopacity, _root.footershadowstrength, _root.footershadowstrength, 1, 1);
			_stage.addChildAt(footer, 2);
			
			t_footer = BetweenAS3.to(footer, {alpha:1},0.5,Sine.easeOut);
			t_footer.play();
			
			_stage.addEventListener(Event.ADDED, onStageResize,false,0,true);
			_stage.addEventListener(Event.RESIZE, onStageResize);
		}
		
		//
		//STAGE RESIZE FUNCTION
		//
	
		private function onStageResize(e:Event):void{
			solidbg.width = _stage.stageWidth;
			solidbg.height = _stage.stageHeight;
			with(pixelbg.graphics){
				clear();
				pixelbg.graphics.beginBitmapFill(pixeltile,null,true,false);
				pixelbg.graphics.drawRect(0,0,_stage.stageWidth,_root.footerheight);
				endFill();
			}
			pixelbg.y = solidbg.y = Math.round(_stage.stageHeight-_root.footerheight);
			txt.x = Math.round(_stage.stageWidth/2)-Math.round(txt.width/2);
			txt.y = pixelbg.y+Math.round(_root.footerheight/2)-Math.round(txt.height/2);
		} 
	}
}