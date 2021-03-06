import javax.sound.sampled.*;

public class EscalaMusical{

  public static final float SAMPLE_RATE = 8000f;

  public static void tone(int hz, int msecs) 
     throws LineUnavailableException 
  {
     tone(hz, msecs, 1.0);
  }

  public static void tone(int hz, int msecs, double vol)
      throws LineUnavailableException 
  {
    byte[] buf = new byte[1];
    AudioFormat af = 
        new AudioFormat(
            SAMPLE_RATE, // sampleRate
            8,           // sampleSizeInBits
            1,           // channels
            true,        // signed
            false);      // bigEndian
    SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
    sdl.open(af);
    sdl.start();
    for (int i=0; i < msecs*8; i++) {
      double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
      buf[0] = (byte)(Math.sin(angle) * 127.0 * vol);
      sdl.write(buf,0,1);
    }
    sdl.drain();
    //sdl.stop();
    //sdl.close();
  }
  
  
  
  public static void tocarNota (int octava, String nota, String figura, int velocidad) 
  throws Exception
  {
    String tonos[] = {      "dob", "do", "do#", "reb","re", "re#","mib","mi","mi#","fab", "fa","fa#","solb","sol","sol#","lab","la","la#","sib","si","si#"};
    int semitonosAlLa [] = {-10,   -9,   -8,    -8,   -7,   -6,   -6,   -5,  -4,  -5,     -4,   -3,   -3,   -2,   -1,     -1,   0,    1,  1,    2,    3};
    
    String figuras[] = {"cuadrada","redonda","redondaP","blanca","blancaP","negra","negraP","corchea","corcheaP","semicorchea","semicorcheaP","fusa","fusaP","semifusa","semifusaP"};
    int duracion[] =  {256,128,192,64,96,32,48,16,24,8,12,4,6,2,3};
    
    int pos=-1;
    for (int i=0; i<tonos.length; i++) {
      if (tonos[i].equals(nota)) {
        pos = i;
      }
    }
    
    int posFig=-1;
    for (int i=0; i<figuras.length; i++) {
      if (figuras[i].equals(figura)) {
        posFig = i;
      }
    }
    
    //System.out.println("Semitonos al LA: "+semitonosAlLa[pos]);
    
    int tonoFrecuencia = 0;
    if (pos != -1) {
      tonoFrecuencia = (int)(440*Math.pow(1.0594630943592953,(octava-4)*12+semitonosAlLa[pos]));
    }
    System.out.println(tonoFrecuencia);
    
    int tiempoSonando = duracion[posFig]*velocidad;
    
    tone(tonoFrecuencia,tiempoSonando);
    Thread.sleep(0);
  }
  

  public static void main(String[] args) throws Exception {
    
    String escala[] = {"do","re","mi","fa","sol","la","si"};
    
    int octavasNotas[] = {5,5,5,5,5,5,5,6};
    String tonosNotas[] = {"do","re","mi","fa","sol","la","si","do"};
    String figurasNotas[] = {"negra","negra","negra","negra","negra","negra","negra","negra"};
  
    
  
   
    
    for (int i=0; i<tonosNotas.length; i++) {
      tocarNota (octavasNotas[i],tonosNotas[i],figurasNotas[i],10);
    }
    
 
  }
  
  
}
