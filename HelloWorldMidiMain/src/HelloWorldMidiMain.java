//Quincy Lin; 2020/9/14;The Unit Test 1 part that was not completed last week has been completed, 
//and the logic of the Generator part needs to be adjusted.



/*
 * c2017-2019 Courtney Brown 
 * 
 * Class: H
 * Description: Demonstration of MIDI file manipulations, etc. & 'MelodyPlayer' sequencer
 * 
 */

import processing.core.*;

import java.util.*; 

//importing the JMusic stuff
import jm.music.data.*;
import jm.JMC;
import jm.util.*;
import jm.midi.*;

import java.io.UnsupportedEncodingException;
import java.net.*;

//import javax.sound.midi.*;

			//make sure this class name matches your file name, if not fix.
public class HelloWorldMidiMain extends PApplet {

	MelodyPlayer player; //play a midi sequence
	MidiFileToNotes midiNotes; //read a midi file
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("HelloWorldMidiMain"); //change this to match above class & file name 

	}

	//setting the window size to 300x300
	public void settings() {
		size(300, 300);

	}

	//doing all the setup stuff
	public void setup() {
		fill(120, 50, 240);

		ProbabilityGenerator<Integer> pitchGenerator = new ProbabilityGenerator<Integer>();
		ProbabilityGenerator<Double> rhythmGenerator = new ProbabilityGenerator<Double>();
		
		
		// returns a url
		String filePath = getPath("mid/MaryHadALittleLamb.mid");
		// playMidiFile(filePath);

		midiNotes = new MidiFileToNotes(filePath); //creates a new MidiFileToNotes -- reminder -- ALL objects in Java must 
													//be created with "new". Note how every object is a pointer or reference. Every. single. one.


//		// which line to read in --> this object only reads one line (or ie, voice or ie, one instrument)'s worth of data from the file
		midiNotes.setWhichLine(0);

		
		pitchGenerator.train(midiNotes.getPitches());
		rhythmGenerator.train(midiNotes.getRhythms());
		
		
		player = new MelodyPlayer(this, 100.0f);

		player.setup();
		player.setMelody(pitchGenerator.generator(20));
		player.setRhythm(rhythmGenerator.generator(20));
	}

	public void draw() {
	//	player.play(); //play each note in the sequence -- the player will determine whether is time for a note onset
		text("Press 1 to start the unit test1!", width/4, height/3);
		text("Press 2 to start the unit test2!", width/4, height/3*2);
		
	}

	//this finds the absolute path of a file
	String getPath(String path) {

		String filePath = "";
		try {
			filePath = URLDecoder.decode(getClass().getResource(path).getPath(), "UTF-8");

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filePath;
	}

	//this function is not currently called. you may call this from setup() if you want to test
	//this just plays the midi file -- all of it via your software synth. You will not use this function in upcoming projects
	//but it could be a good debug tool.
	void playMidiFile(String filename) {
		Score theScore = new Score("Temporary score");
		Read.midi(theScore, filename);
		Play.midi(theScore);
	}

	//this starts & restarts the melody.
	public void keyPressed() {
		MidiFileToNotes midiNotesMary;
		String filePath = getPath("mid/MaryHadALittleLamb.mid");
		
		midiNotesMary = new MidiFileToNotes(filePath); 

		midiNotesMary.setWhichLine(0);

		if (key == ' ') {
			player.reset();
			println("Melody started!");
		}
		else if(key == '1')
		{   
			
			//run unit test 1
			//System.out.println(midiNotesMary.getPitchArray());
			//System.out.println(midiNotesMary.getRhythmArray());
			//System.out.println(midiNotesMary.getPitchArray().size());
			//System.out.println(midiNotesMary.getRhythmArray().size());
			ProbabilityGenerator<Integer> pitchGenerator = new ProbabilityGenerator<Integer>();
			ProbabilityGenerator<Double> rhythmGenerator = new ProbabilityGenerator<Double>();
			pitchGenerator.train(midiNotes.getPitches());
			rhythmGenerator.train(midiNotes.getRhythms());
			//Pitch Probability
			System.out.println("Pitches:\r\n" + 
					"\r\n" + 
					"-----Probability Distribution-----");
			pitchGenerator.getProbability();
			System.out.println();
			for(int i=0; i<pitchGenerator.getProbability().size();i++) {
				
		     System.out.println("Token:"+ pitchGenerator.alphabet.get(i) + "| Probability " + pitchGenerator.getProbability().get(i));
				
			
			}
			//Rhythms Probability
			System.out.println("Rhythms:\r\n" + 
					"\r\n" + 
					"-----Probability Distribution-----");
			rhythmGenerator.getProbability();
			System.out.println();
			for(int i=0; i<rhythmGenerator.getProbability().size();i++) {
				
		     System.out.println("Token:"+ rhythmGenerator.alphabet.get(i) + "| Probability " + rhythmGenerator.getProbability().get(i));
				
			
			}
		}
		
		
		
			else if(key == '2')
			{   
				//run unit test 2
				
				ProbabilityGenerator<Integer> pitchGenerator = new ProbabilityGenerator<Integer>();
				ProbabilityGenerator<Double> rhythmGenerator = new ProbabilityGenerator<Double>();
				
				pitchGenerator.getTotal();
				pitchGenerator.train(midiNotes.getPitches());
				rhythmGenerator.train(midiNotes.getRhythms());
			System.out.println(pitchGenerator.generator(20));
			
			
			
			
			}
		
			else if (key == '3')
			{
				//Pitch Probability
				Integer[] songPitches = midiNotes.getPitches();
				ArrayList<Integer> newSongPitches = new ArrayList<Integer>();
				ProbabilityGenerator<Integer> melodyGenP = new ProbabilityGenerator<Integer>();
				ProbabilityGenerator<Integer> probDistGenP = new ProbabilityGenerator<Integer>();
				melodyGenP.train(songPitches);
				
				for(int i = 0; i<10000;i++) {
					  
					newSongPitches = melodyGenP.generator(20);
					Integer[] newSongArray = new Integer[newSongPitches.size()];
					for(int j =0; j < newSongPitches.size(); j++) {
						newSongArray[j] = newSongPitches.get(j);
					}
				
				    probDistGenP.train(newSongArray);
				}
				System.out.println("Pitches:\r\n" + 
						"\r\n" + 
						"-----Probability Distribution-----");
				melodyGenP.getProbability();
				System.out.println();
				for(int i=0; i<melodyGenP.getProbability().size();i++) {
					
			     System.out.println("Token:"+ probDistGenP.alphabet.get(i) + "| Probability " + probDistGenP.getProbability().get(i));
					
				
				}
				
				//Rhythms Probability
				Double[] songRhythms = midiNotes.getRhythms();
				ArrayList<Double> newSongRhythms = new ArrayList<Double>();
				ProbabilityGenerator<Double> melodyGenR = new ProbabilityGenerator<Double>();
				ProbabilityGenerator<Double> probDistGenR = new ProbabilityGenerator<Double>();
				melodyGenR.train(songRhythms);
				
				for(int i = 0; i<10000;i++) {
					  
					newSongRhythms = melodyGenR.generator(20);
					Double[] newSongArray = new Double[newSongRhythms.size()];
					for(int j =0; j < newSongRhythms.size(); j++) {
						newSongArray[j] = newSongRhythms.get(j);
					}
				
				    probDistGenR.train(newSongArray);
				}
				System.out.println("Rhythms:\r\n" + 
						"\r\n" + 
						"-----Probability Distribution-----");
				melodyGenR.getProbability();
				System.out.println();
				for(int i=0; i<melodyGenR.getProbability().size();i++) {
					
			     System.out.println("Token:"+ probDistGenR.alphabet.get(i) + "| Probability " + probDistGenR.getProbability().get(i));
					
				
				}
				
				
				
			}
	        

		
	}
}
