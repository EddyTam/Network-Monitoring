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
	import flash.events.Event;
	import org.libspark.betweenas3.BetweenAS3;
	import org.libspark.betweenas3.tweens.ITween;
	import org.libspark.betweenas3.events.TweenEvent;
	import org.libspark.betweenas3.easing.Expo;
	import org.libspark.betweenas3.easing.Sine;

	public class createsocial extends MovieClip{
		
		private var _stage:Stage;
		private var _root:MovieClip;

		private var xvar:Number;
		
		//
		//CONSTRUCTOR FOR SOCIAL BUTTONS GENERATION
		//
		
		function createsocial(st:Stage, r:MovieClip) {
			_stage = st;
			_root = r;
			
			_stage.addChild(this);
			_stage.addEventListener(Event.ADDED, onStageResize,false,0,true);
			_stage.addEventListener(Event.RESIZE, onStageResize);
			
			for (var i:int = 0; i < _root.sociallength; i++) {
				xvar = (_root.sociallogosize+_root.sociallogospacing)*i;
				this.addChild(new social(_root, this, xvar, i));
			}
		}
		
		//
		//STAGE RESIZE FUNCTION
		//
		
		private function onStageResize(e:Event):void {
			this.x = Math.round(_stage.stageWidth/2)-Math.round(_root.templatewidth/2)+_root.socialleftpadding;
			//this.x = _root.socialleftpadding;
			this.y = Math.round(_stage.stageHeight)-_root.footerheight-_root.sociallogosize-_root.socialbottompadding;
		}
	}
}