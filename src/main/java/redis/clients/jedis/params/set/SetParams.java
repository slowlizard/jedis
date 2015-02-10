package redis.clients.jedis.params.set;

import java.util.ArrayList;

import redis.clients.jedis.params.Params;
import redis.clients.util.SafeEncoder;

public class SetParams extends Params {

  private static final String XX = "xx";
  private static final String NX = "nx";
  private static final String PX = "px";
  private static final String EX = "ex";

  private SetParams() {}
  
  public static SetParams setParams() {
    return new SetParams();
  }
  
  /**
   * Set the specified expire time, in seconds.
   * @param secondsToExpire
   * @return SetParams
   */
  public SetParams ex(int secondsToExpire) {
    addParam(EX, secondsToExpire);
    return this;
  }
  
  /**
   * Set the specified expire time, in milliseconds.
   * @param millisecondsToExpire
   * @return SetParams
   */
  public SetParams px(long millisecondsToExpire) {
    addParam(PX, millisecondsToExpire);
    return this;
  }
  
  /**
   * Only set the key if it does not already exist.
   * @return SetParams
   */
  public SetParams nx() {
    addParam(NX);
    return this;
  }
  
  /**
   * Only set the key if it already exist.
   * @return SetParams
   */
  public SetParams xx() {
    addParam(XX);
    return this;
  }
  
  public byte[][] getByteParams() {
    ArrayList<byte[]> byteParams = new ArrayList<byte[]>();
    
    if(contains(EX)) {
      byteParams.add(SafeEncoder.encode(EX));
      byteParams.add(SafeEncoder.encode(String.valueOf(getParam(EX))));
    }
    if(contains(PX)) {
      byteParams.add(SafeEncoder.encode(PX));
      byteParams.add(SafeEncoder.encode(String.valueOf(getParam(PX))));
    }
    
    if(contains(NX)) {
      byteParams.add(SafeEncoder.encode(NX));
    }
    if(contains(XX)) {
      byteParams.add(SafeEncoder.encode(XX));
    }
    
    return byteParams.toArray(new byte[byteParams.size()][]);
  }
  
}
