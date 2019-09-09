package com.example.bepsedc3.api;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.RemoteException;

import com.example.bepsedc3.MainActivity;
import com.example.bepsedc3.R;
import com.usdk.apiservice.aidl.printer.FactorMode;
import com.usdk.apiservice.aidl.printer.OnPrintListener;
import com.usdk.apiservice.aidl.printer.PrinterError;
import com.usdk.apiservice.aidl.printer.UPrinter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import h5.printer.PrinterUtils;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Observable;
import usdk.printer.VectorFormat;
import usdk.printer.line.DivisionLine;
import usdk.printer.line.Line;
import usdk.printer.line.TextLine;
import usdk.printer.line.TwoColumnLine;
import usdk.printer.page.Page;

/**
 * Printer API.
 */

public class Printer {
    private static final int WIDTH = 372;
    /**
     * Printer object.
     */
    private UPrinter printer = MainActivity.getDeviceService().getPrinter();

    /**
     * Context.
     */
    private Context context = MainActivity.getContext();

    /**
     * Constructor.
     */

    /**
     * Get status.
     */
    public void getStatus() throws RemoteException {
        int ret = printer.getStatus();
        if (ret != PrinterError.SUCCESS) {
            throw new RemoteException(context.getString(getErrorId(ret)));
        }
    }

    /**
     * Set gray.
     */
    public void setPrnGray(int gray) throws RemoteException {
        printer.setPrnGray(gray);
    }

    /**
     * Print text.
     */
    public void addText(int align, String text) throws RemoteException {
        printer.addText(align, text);
    }

    /**
     * Print barcode.
     */
    public void addBarCode(int align, int codeWith, int codeHeight, String barcode) throws RemoteException {
        printer.addBarCode(align, codeWith, codeHeight, barcode);
    }

    /**
     * Print QR code.
     */
    public void addQrCode(int align, int imageHeight, int ecLevel, String qrCode) throws RemoteException {
        printer.addQrCode(align, imageHeight, ecLevel, qrCode);
    }

    /**
     * Print image.
     */
    public void addImage(int align, byte[] imageData) throws RemoteException {
        printer.addImage(align, imageData);
    }

    /**
     * Feed line.
     */
    public void feedLine(int line) throws RemoteException {
        printer.feedLine(line);
    }

    /**
     * Feed pix.
     */
    public void feedPix(int pix) throws RemoteException {
        printer.feedPix(pix);
    }

    /**
     * Print BMP image.
     */
    public void addBmpImage(int offset, int factor, byte[] imageData) throws RemoteException {
        printer.addBmpImage(offset, factor, imageData);
    }

    /**
     * 打印bmp图片，只支持打印单色不压缩的 bmp 图片。不限制高度，限制图片数据小于512k。
     *
     * <br> 注：该接口要求主控版本至少1.2.32，否则调用无效.
     *
     * @param image
     */
    public void addBmpImage(byte[] image) throws RemoteException {
        printer.addBmpImage(0, FactorMode.BMP1X1, image);
    }

    /**
     * Print BMP image by path.
     */
    public void addBmpPath(int offset, int factor, String bmpPath) throws RemoteException {
        printer.addBmpPath(offset, factor, bmpPath);
    }

    /**
     * Start print.
     */
    public void start(OnPrintListener onPrintListener) throws RemoteException {
        printer.startPrint(onPrintListener);
    }

    public Completable start() {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                printer.startPrint(new OnPrintListener.Stub() {
                    @Override
                    public void onFinish() throws RemoteException {
                        emitter.onComplete();
                    }

                    @Override
                    public void onError(int i) throws RemoteException {
                        emitter.onError(new RemoteException("Printer Error"));

                    }

                });

            }
        });
    }

    /**
     * Set ASC size.
     */
    public void setAscSize(int ascSize) throws RemoteException {
        printer.setAscSize(ascSize);
    }

    /**
     * Set ASC scale.
     */
    public void setAscScale(int ascScale) throws RemoteException {
        printer.setAscScale(ascScale);
    }

    /**
     * Set HZ size.
     */
    public void setHzSize(int hzSize) throws RemoteException {
        printer.setHzSize(hzSize);
    }

    /**
     * Set HZ scale.
     */
    public void setHzScale(int hzScale) throws RemoteException {
        printer.setHzScale(hzScale);
    }

    /**
     * Set X space.
     */
    public void setXSpace(int xSpace) throws RemoteException {
        printer.setXSpace(xSpace);
    }

    /**
     * Set Y space.
     */
    public void setYSpace(int ySpace) throws RemoteException {
        printer.setYSpace(ySpace);
    }

    /**
     * Creator.
     */
    private static class Creator {
        private static final Printer INSTANCE = new Printer();
    }

    /**
     * Get printer instance.
     */
    public static Printer getInstance() {
        return Creator.INSTANCE;
    }

    /**
     * Error code.
     */
    private static Map<Integer, Integer> errorCodes;

    static {
        errorCodes = new Hashtable<>();
        errorCodes.put(PrinterError.SUCCESS, R.string.succeed);
        errorCodes.put(PrinterError.SERVICE_CRASH, R.string.service_crash);
        errorCodes.put(PrinterError.REQUEST_EXCEPTION, R.string.request_exception);
        errorCodes.put(PrinterError.ERROR_PAPERENDED, R.string.printer_paper_ended);
        errorCodes.put(PrinterError.ERROR_HARDERR, R.string.printer_hardware_error);
        errorCodes.put(PrinterError.ERROR_OVERHEAT, R.string.printer_overheat);
        errorCodes.put(PrinterError.ERROR_BUFOVERFLOW, R.string.printer_buffer_overflow);
        errorCodes.put(PrinterError.ERROR_LOWVOL, R.string.printer_low_vol);
        errorCodes.put(PrinterError.ERROR_PAPERENDING, R.string.printer_paper_ending);
        errorCodes.put(PrinterError.ERROR_MOTORERR, R.string.printer_engine_error);
        errorCodes.put(PrinterError.ERROR_PENOFOUND, R.string.printer_pe_not_found);
        errorCodes.put(PrinterError.ERROR_PAPERJAM, R.string.printer_paper_jam);
        errorCodes.put(PrinterError.ERROR_NOBM, R.string.printer_no_bm);
        errorCodes.put(PrinterError.ERROR_BUSY, R.string.printer_busy);
        errorCodes.put(PrinterError.ERROR_BMBLACK, R.string.printer_bm_black);
        errorCodes.put(PrinterError.ERROR_WORKON, R.string.printer_power_on);
        errorCodes.put(PrinterError.ERROR_LIFTHEAD, R.string.printer_lift_head);
        errorCodes.put(PrinterError.ERROR_CUTPOSITIONERR, R.string.printer_cutter_position_error);
        errorCodes.put(PrinterError.ERROR_LOWTEMP, R.string.printer_low_temperature);
    }

    /**
     * Get error id.
     */
    public static int getErrorId(int errorCode) {
        if (errorCodes.containsKey(errorCode)) {
            return errorCodes.get(errorCode);
        }

        return R.string.other_error;
    }

    @SuppressLint("WrongConstant")
    public void addImages(List<byte[]> images) throws RemoteException {
        Printer.getInstance().setYSpace(0);
        Iterator var2 = images.iterator();

        while (var2.hasNext()) {
            byte[] image = (byte[]) var2.next();
            Format format = new Format();
            format.setAlign(1);
            this.addImage(format, image);
        }
    }

    /**
     * Add image.
     * <p>
     * addImage into printer in order
     *
     * @throws RemoteException exception
     */
    public void addImage(byte[] image) throws RemoteException {
        Format format = new Format();
        format.setAlign(Format.ALIGN_LEFT);
        addImage(format, image);
    }

    /**
     * Add image.
     */
    public void addImage(Format format, byte[] image) throws RemoteException {
        printer.addImage(format.getAlign(), image);
    }

    /**
     * Init web view.
     * <p>
     * Just invoke once when application start
     *
     * @param context context
     */
    public static void initWebView(Context context) {
        PrinterUtils.initWebView(context, WIDTH);
    }

    /**
     * Print.
     *
     * @return Single allows getting print results using Rx .
     */
    public Completable print() {
        return Completable.create(e -> printer.startPrint(new com.usdk.apiservice.aidl.printer.OnPrintListener.Stub() {
            @Override
            public void onFinish() throws RemoteException {
                e.onComplete();
            }

            @Override
            public void onError(int errorCode) throws RemoteException {
                e.onError(new Exception("nymph_printer_print_error"));
            }
        }));
    }

    /**
     * Add vector text width default style.
     *
     * @param text Print text
     * @see #addVectorText(String, VectorFormat)
     * @see #addVectorText(String, VectorFormat, String, VectorFormat)
     */
    public void addVectorText(String text) throws IOException, RemoteException {
        this.addVectorText(text, new VectorFormat());
    }


    /**
     * Add 2 column layout vector text. The left text aligns left, the right aligns right.
     *
     * @param left  left text.
     * @param right right text.
     * @see #addVectorText(String)
     */
    public void addVectorText(String left, String right) throws IOException, RemoteException {
        this.addVectorText(left, new VectorFormat(), right, new VectorFormat());
    }


    /**
     * Add vector separator with specified line thickness and line height.
     *
     * @param thickness thickness
     */
    public void addVectorSeparator(int thickness, int height) throws RemoteException, IOException {
        addVectorSeparator(new DivisionLine(thickness, height));
    }

    /**
     * Add vector separator with specified line thickness.
     *
     * @param thickness thickness
     */
    public void addVectorSeparator(int thickness) throws RemoteException, IOException {
        addVectorSeparator(new DivisionLine(thickness));
    }

    /**
     * Add vector separator with default line thickness {@link DivisionLine#thickness}.
     */
    public void addVectorSeparator() throws RemoteException, IOException {
        addVectorSeparator(new DivisionLine());
    }

    /**
     * Add vector separator.
     */
    private void addVectorSeparator(DivisionLine line) throws RemoteException, IOException {

        // add to printer buffer.
        addBmpImage(0, FactorMode.BMP1X1, line.generateBmpImage());

    }

    /**
     * Add vector lines.
     * <p>
     * Used to add many lines(> 100 lines) to printer.
     *
     * @param lines lines.vector
     * @return
     */
    public Completable addVectorLines(List<Line> lines) {
        return Observable.fromIterable(Page.paginate(printer, lines))
                .flatMapSingle(Page::prepare)
                .sorted((page1, page2) -> Integer.compare(page1.index, page2.index))
                .doOnNext(page -> page.push())
                .ignoreElements();
    }

    /**
     * Add vector lines.
     * <p>
     * Used to add many lines(> 60 lines) to printer.
     *
     * @param texts texts.
     * @return
     */
    public Completable addVectorTexts(List<String> texts) {
        List<Line> lines = new ArrayList<>();
        for (String text : texts) {
            lines.add(new TextLine(text));
        }

        return addVectorLines(lines);
    }

    /**
     * Add 2 column layout vector text. The left text aligns left, the right aligns right.
     *
     * @param left        left text.
     * @param leftFormat  left format. The alignment is ignored and the line space is not import,
     *                    because only one line is limited for left content.
     * @param right       right text. The alignment is ignored.
     * @param rightFormat right format.
     * @see #addVectorText(String)
     */
    public void addVectorText(String left, VectorFormat leftFormat, String right, VectorFormat rightFormat) throws RemoteException, IOException {

        TwoColumnLine line = new TwoColumnLine(left, leftFormat, right, rightFormat);

        printer.setYSpace(Math.max(leftFormat.lineSpace, rightFormat.lineSpace));

        // add to printer buffer.
        printer.addBmpImage(0, FactorMode.BMP1X1, line.generateBmpImage());

    }

    /**
     * Add vector text with config.
     *
     * <p>
     * If the text is too long in one line, it can be auto wrapped.
     * Also you can use \n or call the interface multi times to split lines.
     *
     * @param text   text.
     * @param format format.
     */
    public void addVectorText(String text, VectorFormat format) throws RemoteException, IOException {
        if (format == null) {
            format = new VectorFormat();
        }

        TextLine line = new TextLine(text, format);

        // add to printer buffer.
        setYSpace(format.lineSpace);
        printer.addBmpImage(0, FactorMode.BMP1X1, line.generateBmpImage());

    }
}
