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

	public class createbg extends MovieClip{
		
		private var _stage:Stage;
		private var _root:MovieClip;
		
		public var backgroundGradient:Sprite = new Sprite();
		public var image:MovieClip = new MovieClip();
		
		public var image_array:Array = new Array();
		
		private var t_gradient:ITween;
		
		public var loadedvar:Boolean = false;
		
		//
		//CONSTRUCTOR
		//
		
		function createbg(st:Stage, r:MovieClip){
			_stage = st;
			_root = r;
			
			backgroundGradient.alpha = 1;
			_stage.addChildAt(image, 0);
			_stage.addChildAt(backgroundGradient, 1);
			
			_stage.addEventListener(Event.ADDED, onStageResize,false,0,true);
			_stage.addEventListener(Event.RESIZE, onStageResize);
		}
		
		public function initImage():void{
			var tempimage:Object = new bgimage(_stage, _root, image, this);
			image_array.push(tempimage);
			image_array[image_array.length-1].addImage();
		}
		
		public function killImages():void{
			for (var i:int = 0; i < image_array.length-1; i++) {
				image_array[i].removeImage();
				image_array[i] = null;
				image_array.splice(i,1);
			}
		}
		
		public function stopLoaders():void{
			for (var i:int = 0; i < image_array.length-1; i++) {
				image_array[i].stopLoading();
			}
		}

		//
		//STAGE RESIZE FUNCTION
		//
	
		private function onStageResize(e:Event):void{

			var colors:Array = [_root.bggradientcolor1, _root.bggradientcolor2];
			var alphas:Array = [0, _root.bggradientalpha];
			var ratios:Array = [_root.bggradientratio1, _root.bggradientratio2];
			var matrix:Matrix = new Matrix();
			matrix.createGradientBox(_stage.stageWidth, _stage.stageHeight, 0, 0, 0);
			var focalPoint:Number = 0;
			
			with(backgroundGradient.graphics){
				clear();
				beginGradientFill(GradientType.RADIAL, colors, alphas, ratios, matrix, SpreadMethod.PAD, InterpolationMethod.RGB, focalPoint);
				drawRect(0, 0, _stage.stageWidth, _stage.stageHeight);
				endFill();
			}
			
		} 
	}
}