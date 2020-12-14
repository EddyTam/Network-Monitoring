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
	import flash.geom.ColorTransform;
	import flash.geom.Matrix;
	import flash.display.Bitmap;
	import flash.display.BitmapData;
	import flash.display.StageDisplayState;
	import flash.net.URLLoader;
	import flash.net.URLRequest;
	import flash.display.Loader;
	import flash.text.TextField;
	import flash.text.TextFormat;
	import flash.text.TextFieldAutoSize;
	import flash.text.AntiAliasType;
	import flash.text.GridFitType;
	import flash.media.Sound;
	import flash.media.SoundChannel;
	import flash.media.SoundTransform;
	import flash.system.LoaderContext;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.events.IOErrorEvent;
	import flash.events.FullScreenEvent;
	import org.libspark.betweenas3.BetweenAS3;
	import org.libspark.betweenas3.tweens.ITween;
	import org.libspark.betweenas3.events.TweenEvent;
	import org.libspark.betweenas3.easing.Expo;
	import org.libspark.betweenas3.easing.Quad;
	import org.libspark.betweenas3.easing.Quint;
	import org.libspark.betweenas3.easing.Sine;

	public class createutil extends MovieClip{
		
		private var _stage:Stage;
		private var _root:MovieClip;
		
		private var music:musicicon = new musicicon();
		private var fullscreen:fullscreenicon = new fullscreenicon();
		
		private var musicmc:MovieClip = new MovieClip();
		private var fullscreenmc:MovieClip = new MovieClip();
		
		public var musicplayerholder:MovieClip = new MovieClip();
		private var musicplayermc:MovieClip = new MovieClip();
		private var musicplayermask:Sprite;
		private var solidbg:Sprite = new Sprite();
		private var pixel:Sprite = new Sprite();
		private var bgTile:BitmapData;
		private var bg:Sprite = new Sprite();
		
		private var mc_volume:MovieClip = new MovieClip();
		private var mc_volumebg:MovieClip = new MovieClip();
		private var mc_volumebar:MovieClip = new MovieClip();
		private var mc_interfacebg:MovieClip = new MovieClip();
		private var mc_close:MovieClip = new MovieClip();
		private var mc_play:MovieClip = new MovieClip();
		private var mc_pause:MovieClip = new MovieClip();
		private var icon_volume:MovieClip = new music_volume();
		private var icon_close:MovieClip = new music_close();
		private var icon_play:MovieClip = new music_play();
		private var icon_pause:MovieClip = new music_pause();
		private var icon_volume_ct:ColorTransform = icon_volume.transform.colorTransform;
		private var icon_close_ct:ColorTransform = icon_volume.transform.colorTransform;
		private var icon_play_ct:ColorTransform = icon_play.transform.colorTransform;
		private var icon_pause_ct:ColorTransform = icon_pause.transform.colorTransform;
		private var interface_volume:Sprite = new Sprite();
		private var interface_volumebg:Sprite = new Sprite();
		private var interface_volumebar:Sprite = new Sprite();
		private var interface_play:Sprite = new Sprite();
		private var interface_pause:Sprite = new Sprite();
		private var interface_close:Sprite = new Sprite();
		
		private var songs:Array = new Array();
		private var maskheight:int;
		private var maskwidth:int;
		private var playeropen:Boolean = false;
		public var isplaying:Boolean = false;
		private var volumesave:Number;
		private var startvolume:Number;
		public var activesong:MovieClip;
		public var activesongid:int = 0;
		
		private var soundRequest:URLRequest;
		private var sound:Sound;
		private var channel:SoundChannel;
		private var stransform:SoundTransform;
		private var mute:Boolean = false;
		public var pausevar:Boolean;
		public var activepausevar:Boolean;
		private var songresumepos:Number;
		
		private var music_txt:TextField = new TextField();
		private var fullscreen_txt:TextField = new TextField();
		private var myFormat:TextFormat = new TextFormat();
		
		private var t_music:ITween;
		private var t_fullscreen:ITween;
		private var t_player:ITween;
		private var mc_play_over:ITween;
		private var mc_play_out:ITween;
		private var mc_pause_over:ITween;
		private var mc_pause_out:ITween;
		private var mc_close_over:ITween;
		private var mc_close_out:ITween;
		private var mc_volume_over:ITween;
		private var mc_volume_out:ITween;
		
		//
		//CONSTRUCTOR
		//
		
		function createutil(st:Stage, r:MovieClip){
			_stage = st;
			_root = r;
			
			myFormat.font = _root.textfont;
			myFormat.indent = 0;
			myFormat.color = _root.musicfullscreencolor;
			myFormat.size = _root.musicfullscreentextsize;
			
			music_txt.multiline = false;
			music_txt.condenseWhite = true;
			music_txt.embedFonts = true;
			music_txt.selectable = false;
			music_txt.antiAliasType = AntiAliasType.ADVANCED;
			music_txt.autoSize = TextFieldAutoSize.LEFT;
			music_txt.gridFitType = GridFitType.PIXEL;
			music_txt.text = _root.musicplayerlabel;
			music_txt.setTextFormat(myFormat);
			music_txt.mouseEnabled = false;
			
			fullscreen_txt.multiline = false;
			fullscreen_txt.condenseWhite = true;
			fullscreen_txt.embedFonts = true;
			fullscreen_txt.selectable = false;
			fullscreen_txt.antiAliasType = AntiAliasType.ADVANCED;
			fullscreen_txt.autoSize = TextFieldAutoSize.LEFT;
			fullscreen_txt.gridFitType = GridFitType.PIXEL;
			fullscreen_txt.text = _root.fullscreenlabel;
			fullscreen_txt.setTextFormat(myFormat);
			fullscreen_txt.mouseEnabled = false;
			
			music_txt.y = fullscreen_txt.y = Math.round((music.height/2)-(music_txt.height/2));
			music.x = Math.round(music_txt.width);
			fullscreen.x = Math.round(fullscreen_txt.width);
			
			colorTrans(music, _root.musicfullscreencolor);
			colorTrans(fullscreen, _root.musicfullscreencolor);
			
			musicmc.addChild(music_txt);
			musicmc.addChild(music);
			fullscreenmc.addChild(fullscreen_txt);
			fullscreenmc.addChild(fullscreen);
			
			var m_shadow:dropshadow = new dropshadow(_root, musicmc, _root.assetshadowdistance, 45, _root.assetshadowcolor, _root.assetshadowopacity, _root.assetshadowblur, _root.assetshadowblur, _root.assetshadowstrength, 1);
			var f_shadow:dropshadow = new dropshadow(_root, fullscreenmc, _root.assetshadowdistance, 45, _root.assetshadowcolor, _root.assetshadowopacity, _root.assetshadowblur, _root.assetshadowblur, _root.assetshadowstrength, 1);
			musicmc.alpha = fullscreenmc.alpha = 0;
			
			musicmc.mouseEnabled = fullscreenmc.mouseEnabled = true;
			musicmc.buttonMode = fullscreenmc.buttonMode = true;

			startvolume = volumesave = _root.musicstartvolume;
			
			_stage.addChild(this);

			if(_root.musicactivated=="true"){
				this.addChild(musicmc);
			}
			this.addChild(fullscreenmc);
			
			if(_root.musicactivated=="true"){
				BetweenAS3.apply(musicmc, {_blurFilter: {blurX: 5,blurY: 5, quality:1}});
				BetweenAS3.apply(fullscreenmc, {_blurFilter: {blurX: 5,blurY: 5, quality:1}});
				var t1:ITween = BetweenAS3.to(musicmc,{alpha:_root.musicfullscreenopacity},1,Expo.easeOut);
				var t2:ITween = BetweenAS3.to(musicmc,{_blurFilter:{blurX: 0, blurY: 0, quality:1}},1,Sine.easeOut);
				var m:ITween = BetweenAS3.parallel(t1,t2);
				m.play();
			}
			var t3:ITween = BetweenAS3.to(fullscreenmc,{alpha:_root.musicfullscreenopacity},1,Expo.easeOut);
			var t4:ITween = BetweenAS3.to(fullscreenmc,{_blurFilter:{blurX: 0, blurY: 0, quality:1}},1,Sine.easeOut);
			var f:ITween = BetweenAS3.parallel(t3,t4);
			f.play();
			
			if(_root.musicactivated=="true"){
				musicmc.addEventListener(MouseEvent.MOUSE_OVER, m_mouse_over);
				musicmc.addEventListener(MouseEvent.MOUSE_OUT, m_mouse_out);
				musicmc.addEventListener(MouseEvent.CLICK, m_mouse_press);
				initMusicPlayer();
			}
			fullscreenmc.addEventListener(MouseEvent.MOUSE_OVER, f_mouse_over);
			fullscreenmc.addEventListener(MouseEvent.MOUSE_OUT, f_mouse_out);
			fullscreenmc.addEventListener(MouseEvent.CLICK, startFullscreen);
	
			_stage.addEventListener(Event.FULLSCREEN, fullScreenMode);
			_stage.addEventListener(Event.ADDED, onStageResize,false,0,true);
			_stage.addEventListener(Event.RESIZE, onStageResize);
		}
		
		private function initMusicPlayer():void {
			
			maskheight = (_root.musiclength*_root.musicentryheight)+_root.musicvolumeheight+(_root.musicplayerpadding*2);
			maskwidth = _root.musicplayerwidth+(_root.musicplayerpadding*2);
			var ypos:int = 0;
			
			musicplayermask = new Sprite();
			musicplayermask.graphics.beginFill(0x000000);
			musicplayermask.graphics.drawRect(0, 0, maskwidth, maskheight);
			musicplayermask.graphics.endFill();
			musicplayermask.mouseEnabled = false;
			
			musicplayermc.x = _root.musicplayerpadding;
			musicplayermc.y = _root.musicplayerpadding+_root.musicvolumeheight;
			
			musicplayerholder.mouseEnabled = false;
			musicplayerholder.mask = musicplayermask;
			
			solidbg.graphics.beginFill(_root.footerbgcolor);
			solidbg.graphics.drawRect(0, 0, maskwidth, maskheight);
			solidbg.graphics.endFill();
			pixel.graphics.beginFill(_root.footerpixelcolor, 1);
			pixel.graphics.drawRect(0, 0, 1, 1);
			pixel.graphics.drawRect(1, 1, 1, 1);
			pixel.graphics.endFill();
			bgTile = new BitmapData(2, 2, true, _root.footerpixelcolor);
			bgTile.draw(pixel);
			bg.graphics.beginBitmapFill(bgTile,null,true,false);
			bg.graphics.drawRect(0,0, maskwidth, maskheight);
			bg.graphics.endFill();
			
			musicplayerholder.addChild(solidbg);
			musicplayerholder.addChild(bg);
			musicplayerholder.addChild(musicplayermc);
			
			for(var i:int = 0;i < _root.musiclength;i++){
				songs[i] = new song(_root, _stage, musicplayermc, i, ypos, this);
				ypos += _root.musicentryheight;
			}
			
			createVolume();
			createPlayPause();
			createClose();
			addPlayerListeners();
			
			_stage.addChildAt(musicplayerholder,5);
			_stage.addChildAt(musicplayermask,6);
		}
		
		//
		//SHOW AND HIDE UTIL
		//
		
		public function showUtil():void {
			musicplayerholder.visible = true;
			musicmc.visible = true;
			fullscreenmc.visible = true;
		}
		
		public function hideUtil():void {
			musicplayerholder.visible = false;
			musicmc.visible = false;
			fullscreenmc.visible = false;
		}
		
		//
		//VOLUME
		//
		
		private function createVolume():void {
			
			interface_volume.graphics.beginFill(_root.musicentrybgcolor);
			interface_volume.graphics.drawRect(0, 0, _root.musicplayerwidth, _root.musicvolumeheight);
			interface_volume.graphics.endFill();
			interface_volume.mouseEnabled = false;
			
			icon_volume.x = 10;
			icon_volume.y = Math.round((_root.musicvolumeheight/2)-(icon_volume.height/2));
			icon_volume_ct.color = _root.musiciconcolor;
			icon_volume.transform.colorTransform = icon_volume_ct;
			
			interface_volumebg.graphics.beginFill(_root.musicvolumebgcolor);
			interface_volumebg.graphics.drawRect(0, 0, 1, 5);
			interface_volumebg.graphics.endFill();
			interface_volumebg.mouseEnabled = false;
			
			interface_volumebar.graphics.beginFill(_root.musicvolumecolor);
			interface_volumebar.graphics.drawRect(0, 0, 1, 5);
			interface_volumebar.graphics.endFill();
			interface_volumebar.mouseEnabled = false;
			
			mc_volume.addChild(interface_volume);
			mc_volume.addChild(icon_volume);
			mc_volumebg.addChild(interface_volumebg);
			mc_volumebar.addChild(interface_volumebar);
			
			mc_volumebar.mouseEnabled = false;
			mc_volumebg.mouseEnabled = true;
			mc_volumebg.buttonMode = true;
			icon_volume.buttonMode = true;
			
			mc_volume.addChild(mc_volumebg);
			mc_volume.addChild(mc_volumebar);
			
			mc_volumebg.width = _root.musicplayerwidth-30-icon_volume.width-(_root.musicvolumeheight*2);
			mc_volumebar.width = Math.round(mc_volumebg.width*startvolume);
			mc_volume.x = mc_volume.y = _root.musicplayerpadding;
			mc_volumebg.x = mc_volumebar.x = _root.musicplayerpadding+25;
			mc_volumebg.y = mc_volumebar.y = Math.round((_root.musicvolumeheight/2)-(mc_volumebar.height/2))+_root.musicplayerpadding;
			
			musicplayerholder.addChild(mc_volume);
			musicplayerholder.addChild(mc_volumebg);
			musicplayerholder.addChild(mc_volumebar);
		}
		
		private function createPlayPause():void {
			interface_play.graphics.beginFill(_root.musicbuttoncolor);
			interface_play.graphics.drawRect(0, 0, _root.musicvolumeheight, _root.musicvolumeheight);
			interface_play.graphics.endFill();
			interface_play.mouseEnabled = false;
			interface_pause.graphics.beginFill(_root.musicbuttoncolor);
			interface_pause.graphics.drawRect(0, 0, _root.musicvolumeheight, _root.musicvolumeheight);
			interface_pause.graphics.endFill();
			interface_pause.mouseEnabled = false;
			
			icon_play.x = Math.round((_root.musicvolumeheight/2)-(icon_play.width/2));
			icon_play.y = Math.round((_root.musicvolumeheight/2)-(icon_play.height/2));
			icon_play_ct.color = _root.musiciconcolor;
			icon_play.transform.colorTransform = icon_play_ct;
			icon_pause.x = Math.round((_root.musicvolumeheight/2)-(icon_pause.width/2));
			icon_pause.y = Math.round((_root.musicvolumeheight/2)-(icon_pause.height/2));
			icon_pause_ct.color = _root.musiciconcolor;
			icon_pause.transform.colorTransform = icon_pause_ct;
			
			mc_play.addChild(interface_play);
			mc_play.addChild(icon_play);
			mc_pause.addChild(interface_pause);
			mc_pause.addChild(icon_pause);
			mc_play.x = mc_pause.x = _root.musicplayerwidth-(_root.musicvolumeheight*2)+_root.musicplayerpadding-1;
			mc_play.y = mc_pause.y = _root.musicplayerpadding;
			mc_play.buttonMode = mc_pause.buttonMode = true;
			mc_play.alpha = mc_pause.alpha = 0.4;
			
			//if(_root.musicautoplay=="true"){
				mc_play.visible = false;
				pausevar = false;
				activepausevar = false;
				songs[0].selectEntry();
				/*
			}else{
				mc_pause.visible = false;
				pausevar = true;
				activepausevar = true;
			}
			*/
			
			musicplayerholder.addChild(mc_play);
			musicplayerholder.addChild(mc_pause);
		}
		
		private function createClose():void {
			interface_close.graphics.beginFill(_root.musicbuttoncolor);
			interface_close.graphics.drawRect(0, 0, _root.musicvolumeheight, _root.musicvolumeheight);
			interface_close.graphics.endFill();
			interface_close.mouseEnabled = false;
			
			icon_close.x = Math.round((_root.musicvolumeheight/2)-(icon_close.width/2));
			icon_close.y = Math.round((_root.musicvolumeheight/2)-(icon_close.height/2));
			icon_close_ct.color = _root.musiciconcolor;
			icon_close.transform.colorTransform = icon_volume_ct;
			
			mc_close.addChild(interface_close);
			mc_close.addChild(icon_close);
			mc_close.x = _root.musicplayerwidth-_root.musicvolumeheight+_root.musicplayerpadding;
			mc_close.y = _root.musicplayerpadding;
			mc_close.buttonMode = true;
			mc_close.alpha = 0.4;
			
			musicplayerholder.addChild(mc_close);
		}
		
		//
		//PLAY SONG FUNCTION
		//
		
		public function playSong():void {
			isplaying = true;
			
			soundRequest = new URLRequest(_root.musicdata_array[activesongid]["urllink"]);
			sound = new Sound();
			channel = new SoundChannel();
			stransform = new SoundTransform(1, 0);  
			
			sound.addEventListener(IOErrorEvent.IO_ERROR, function(evt:IOErrorEvent):void {},false,0,true);
			sound.addEventListener(Event.COMPLETE, startPlay);
			
			sound.load(soundRequest);
			if(!mute){
				stransform.volume = volumesave; 
			}else{
				stransform.volume = 0;
			}
		}
		
		public function stopSong():void {
			
			if(sound.hasEventListener(Event.COMPLETE)){
				sound.removeEventListener(Event.COMPLETE, startPlay);
			}
	
			channel.stop();
			
			soundRequest = null;
			sound = null;
			channel = null;
			stransform = null;
		}
		
		//
		//EXTERNAL PLAY PAUSE
		//
		
		public function extPause():void {
			if(_root.musicactivated=="true" && !activepausevar){
				pausevar = true;
				mc_play.visible = true;
				mc_pause.visible = false;
				songresumepos = channel.position;
			 	channel.stop();
			}
		}
		
		public function extPlay():void {
			if(_root.musicactivated=="true" && !activepausevar){
				pausevar = false;
				mc_play.visible = false;
				mc_pause.visible = true;
				channel = sound.play(songresumepos);
				volumeSet(volumesave);
				mc_volumebar.width = Math.round(mc_volumebg.width*volumesave);
				mc_volume.alpha = 1;
			}
		}
		
		//
		//START MUSIC AND PLAY NEXT
		//
		
		private function startPlay(e:Event):void {
			channel = sound.play(0);
			channel.soundTransform = stransform; 
			
			if(channel.hasEventListener(Event.SOUND_COMPLETE)){
				channel.removeEventListener(Event.SOUND_COMPLETE, playNextSong);
			}
			channel.addEventListener(Event.SOUND_COMPLETE, playNextSong);
		}
		
		private function playNextSong(e:Event):void {
			if(activesongid<_root.musiclength-1){
				activesongid++;
			}else if (activesongid==_root.musiclength-1){
				activesongid = 0;
			}
			songs[activesongid].selectEntry();
		}
		
		//
		//MUSIC PLAYER LISTENERS
		//
		
		private function addPlayerListeners():void {
		
			mc_close.addEventListener(MouseEvent.MOUSE_OVER, mc_close_overf,false,0,true);
			mc_close.addEventListener(MouseEvent.MOUSE_OUT, mc_close_outf,false,0,true);
			mc_close.addEventListener(MouseEvent.CLICK, mc_close_clickf);
			mc_play.addEventListener(MouseEvent.MOUSE_OVER, mc_play_overf,false,0,true);
			mc_play.addEventListener(MouseEvent.MOUSE_OUT, mc_play_outf,false,0,true);
			mc_play.addEventListener(MouseEvent.CLICK, mc_play_clickf);
			mc_pause.addEventListener(MouseEvent.MOUSE_OVER, mc_pause_overf,false,0,true);
			mc_pause.addEventListener(MouseEvent.MOUSE_OUT, mc_pause_outf,false,0,true);
			mc_pause.addEventListener(MouseEvent.CLICK, mc_pause_clickf);
			icon_volume.addEventListener(MouseEvent.MOUSE_OVER, mc_volume_overf,false,0,true);
			icon_volume.addEventListener(MouseEvent.MOUSE_OUT, mc_volume_outf,false,0,true);
			icon_volume.addEventListener(MouseEvent.CLICK, mc_volume_clickf);
			_stage.addEventListener(MouseEvent.MOUSE_UP, mouseRelease);
			mc_volumebg.addEventListener(MouseEvent.MOUSE_DOWN, volumeScrubFunction);
			
		}
		
		//
		//CLOSE BUTTON
		//
		
		private function mc_close_overf(e:MouseEvent):void {
			if (mc_close_out!=null) {
				mc_close_out.stop();
			}
			mc_close_over = BetweenAS3.to(mc_close, {alpha:1},0.7,Expo.easeOut);
			mc_close_over.play();
		}
		
		private function mc_close_outf(e:MouseEvent):void {
			if (mc_close_over!=null) {
				mc_close_over.stop();
			}
			mc_close_out = BetweenAS3.to(mc_close, {alpha:0.4},0.7,Expo.easeOut);
			mc_close_out.play();
		}
		
		private function mc_close_clickf(e:MouseEvent):void {
			m_mouse_press(null);
		}
		
		//
		//PLAY BUTTON
		//
		
		private function mc_play_overf(e:MouseEvent):void {
			if (mc_play_out!=null) {
				mc_play_out.stop();
			}
			mc_play_over = BetweenAS3.to(mc_play, {alpha:1},0.7,Expo.easeOut);
			mc_play_over.play();
		}
		
		private function mc_play_outf(e:MouseEvent):void {
			if (mc_play_over!=null) {
				mc_play_over.stop();
			}
			mc_play_out = BetweenAS3.to(mc_play, {alpha:0.4},0.7,Expo.easeOut);
			mc_play_out.play();
		}
		
		private function mc_play_clickf(e:MouseEvent):void {
			if(pausevar){
				pausevar = false;
				activepausevar = false;
				mc_play.visible = false;
				mc_pause.visible = true;
				channel = sound.play(songresumepos);
				volumeSet(volumesave);
			}
		}
		
		public function unPause():void {
			pausevar = false;
			mc_play.visible = false;
			mc_pause.visible = true;
		}
		
		//
		//PAUSE BUTTON
		//
		
		private function mc_pause_overf(e:MouseEvent):void {
			if (mc_pause_out!=null) {
				mc_pause_out.stop();
			}
			mc_pause_over = BetweenAS3.to(mc_pause, {alpha:1},0.7,Expo.easeOut);
			mc_pause_over.play();
		}
		
		private function mc_pause_outf(e:MouseEvent):void {
			if (mc_pause_over!=null) {
				mc_pause_over.stop();
			}
			mc_pause_out = BetweenAS3.to(mc_pause, {alpha:0.4},0.7,Expo.easeOut);
			mc_pause_out.play();
		}
		
		private function mc_pause_clickf(e:MouseEvent):void {
			if(!pausevar){
				pausevar = true;
				activepausevar = true;
				mc_play.visible = true;
				mc_pause.visible = false;
				songresumepos = channel.position;
			 	channel.stop();
			}
		}
		
		//
		//VOLUME MUTE BUTTON
		//
		
		private function mc_volume_overf(e:MouseEvent):void {
			if (mc_volume_out!=null) {
				mc_volume_out.stop();
			}
			mc_volume_over = BetweenAS3.to(icon_volume, {alpha:0.4},0.7,Expo.easeOut);
			mc_volume_over.play();
		}
		
		private function mc_volume_outf(e:MouseEvent):void {
			if (mc_volume_over!=null) {
				mc_volume_over.stop();
			}
			mc_volume_out = BetweenAS3.to(icon_volume, {alpha:1},0.7,Expo.easeOut);
			mc_volume_out.play();
		}
		
		private function mc_volume_clickf(e:MouseEvent):void {
			if(!mute){
				mute = true;
				volumeSet(0);
				mc_volumebar.width = 0;
				mc_volume.alpha = 0.4;
			}else{
				mute = false;
				volumeSet(volumesave);
				mc_volumebar.width = Math.round(mc_volumebg.width*volumesave);
				mc_volume.alpha = 1;
			}
		}
		
		//
		//VOLUME SCRUBBING
		//
		
		private function volumeScrubFunction(e:MouseEvent):void {
			mute = false;
			mc_volume.alpha = 1;
			mc_volumebar.addEventListener(Event.ENTER_FRAME, volumeScrubFunctionFrame,false,0,true);
		}
		
		private function volumeScrubFunctionFrame(e:Event):void {
			if(mc_volumebg.hitTestPoint(_stage.mouseX, _stage.mouseY, false)){
				mc_volumebar.width = Math.round(mc_volume.mouseX-mc_volumebar.x)+_root.musicplayerpadding;
				volumesave = (mc_volumebar.width/mc_volumebg.width);
				volumeSet(volumesave);
			}
		}
		
		private function volumeSet(vol:Number):void {
			stransform.volume = vol;
			channel.soundTransform = stransform; 
		}
		
		//
		//MOUSE UP
		//
		
		private function mouseRelease(e:MouseEvent):void {
			if (mc_volumebar.hasEventListener(Event.ENTER_FRAME)) {
				mc_volumebar.removeEventListener(Event.ENTER_FRAME, volumeScrubFunctionFrame);
			}
		}
		
		//
		//MOUSE INTERACTION FUNCTIONS
		//
		
		private function m_mouse_over(event):void {
			if (t_music!=null) {
				t_music.stop();
			}
			t_music = BetweenAS3.to(musicmc,{alpha:1},0.4,Expo.easeOut);
			t_music.play();
		}
		
		private function m_mouse_out(event):void {
			if (t_music!=null) {
				t_music.stop();
			}
			t_music = BetweenAS3.to(musicmc,{alpha:_root.musicfullscreenopacity},0.4,Expo.easeOut);
			t_music.play();
		}
		
		private function m_mouse_press(event):void {
			if (t_player!=null) {
				t_player.stop();
			}
			if(!playeropen){
				playeropen = true;
				musicplayerholder.mouseEnabled = true;
				t_player = BetweenAS3.to(musicplayermask,{y:musicmc.y-maskheight-10},0.8,Expo.easeInOut);
			}else{
				playeropen = false;
				musicplayerholder.mouseEnabled = false;
				t_player = BetweenAS3.to(musicplayermask,{y:musicmc.y-10},0.8,Expo.easeInOut);
			}
			t_player.play();
		}
		
		private function f_mouse_over(event):void {
			if (t_fullscreen!=null) {
				t_fullscreen.stop();
			}
			t_fullscreen = BetweenAS3.to(fullscreenmc,{alpha:1},0.4,Expo.easeOut);
			t_fullscreen.play();
		}
		
		private function f_mouse_out(event):void {
			if (t_fullscreen!=null) {
				t_fullscreen.stop();
			}
			t_fullscreen = BetweenAS3.to(fullscreenmc,{alpha:_root.musicfullscreenopacity},0.4,Expo.easeOut);
			t_fullscreen.play();
		}
		
		//
		//FULLSCREEN MODE FUNCTIONALITY
		//
		
		private function fullScreenMode(event:FullScreenEvent):void {
			if (event.fullScreen) {
				_root.fsvar_stage=true;
			}else {
				_root.fsvar_stage=false;
			}
		};
		
		private function startFullscreen(event:MouseEvent):void {
			if (_stage.displayState == StageDisplayState.NORMAL) {
				_stage.displayState = StageDisplayState.FULL_SCREEN;
				_root.fsvar_stage=true;
			} else {
				_stage.displayState = StageDisplayState.NORMAL;
				_root.fsvar_stage=false;
			}
		}; 
		
		private function colorTrans(target:MovieClip, color:uint):void{
			var colorTransform:ColorTransform = target.transform.colorTransform;
			colorTransform.color = color;
			target.transform.colorTransform = colorTransform;
		}
		
		//
		//STAGE RESIZE FUNCTION
		//
	
		private function onStageResize(e:Event):void{
			fullscreenmc.x = Math.round(_stage.stageWidth/2)+Math.round(_root.templatewidth/2)-Math.round(fullscreenmc.width)-_root.musicfullscreenrightpadding;
			fullscreenmc.y = Math.round(_stage.stageHeight)-_root.footerheight-Math.round(fullscreenmc.height)-_root.musicfullscreenbottompadding;
			if(_root.musicactivated=="true"){
				musicmc.x = fullscreenmc.x-Math.round(musicmc.width)-15;
				musicmc.y = Math.round(_stage.stageHeight)-_root.footerheight-Math.round(musicmc.height)-_root.musicfullscreenbottompadding;
				musicplayerholder.x = musicplayermask.x = musicmc.x-Math.round(_root.musicplayerwidth/2)+Math.round(musicmc.width/2);
				musicplayerholder.y = musicmc.y-maskheight-10;
				if(playeropen){
					musicplayermask.y = musicmc.y-maskheight-10;
				}else{
					musicplayermask.y = musicmc.y-10;
				}
			}
		} 
	}
}